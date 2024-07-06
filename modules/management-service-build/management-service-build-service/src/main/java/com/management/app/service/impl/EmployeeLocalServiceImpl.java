/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;

import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.exception.NoSuchManagerException;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.model.impl.EmployeeImpl;
import com.management.app.service.ManagerLocalService;
import com.management.app.service.base.EmployeeLocalServiceBaseImpl;
import com.management.app.service.persistence.EmployeePersistence;
import com.management.app.service.persistence.impl.constants.EmployeeStatusConstant;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
	property = "model.class.name=com.management.app.model.Employee",
	service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

	@Override
	public Employee addEmployee(
			String firstName, String lastName, String position,
			String stateCode, int status, long managerIdPK, int companyTime,
			long groupId, long companyId, long userId)
		throws NoSuchManagerException {

		_validate(firstName, lastName, position, managerIdPK);

		return _addEmployee(
				firstName, lastName, position, stateCode,
				managerIdPK, groupId, companyId);
	}

	@Override
	public Employee deleteEmployee(long employeeId) throws NoSuchEmployeeException {
		if (_employeePersistence.findByEmployeeId(employeeId) == null) {
			throw new NoSuchEmployeeException(
					"No such employee with id: " + employeeId);
		}
		else {
			_log.debug("Deleting employee with id: " + employeeId);

			return _employeePersistence.remove(employeeId);
		}
	}

	@Override
	public List<Employee> getEmployeesByManagerIdAndPermission(
			long managerIdPK, long companyId, boolean hasPermission)
		throws NoSuchEmployeeException, NoSuchManagerException {

		try {
			if (managerIdPK == -1) {
				return null;
			}

			Manager manager = _managerLocalService.getManager(managerIdPK);

			if (manager == null) {
				throw new NoSuchManagerException(
						"No manager found for id: " + managerIdPK);
			}

			List<Employee> employees = _employeePersistence.findAll();

			if (employees.isEmpty()) {
				throw new NoSuchEmployeeException(
						"No found employee associated to the manager id: " +
								managerIdPK);
			}

			return employees;
		}
		catch (RuntimeException | PortalException runtimeException) {
			if (runtimeException instanceof NoSuchEmployeeException) {
				throw new NoSuchEmployeeException(runtimeException);
			}
			else if (runtimeException instanceof NoSuchManagerException) {
				throw new NoSuchManagerException(runtimeException);
			}
			else {
				// default RuntimeException

				throw new RuntimeException(runtimeException);
			}
		}

	}

	private Employee _addEmployee(
			String firstName, String lastName, String position, String stateCode,
			long managerIdPK, long groupId, long companyId) {

		Employee employee = new EmployeeImpl();

		long employeeId = CounterLocalServiceUtil.increment();

		employee.setEmployeeId(employeeId);
		employee.setCreateDate(new Date());
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setManagerIdPK(managerIdPK);
		employee.setPosition(position);
		employee.setPrimaryKey(employeeId);
		employee.setStateCode(stateCode);
		employee.setStatus(EmployeeStatusConstant.ACTIVE);
		employee.setGroupId(groupId);
		employee.setCompanyId(companyId);
		employee.setMvccVersion(0);

		_log.info("Create employee " + employee.getFirstName());

		return _employeePersistence.update(employee);
	}

	private void _validate(
			String firstName, String lastName, String position, long managerIdPK)
		throws NoSuchManagerException {

		try {
			if (_validRegex(firstName) && _validRegex(lastName)) {
				throw new RuntimeException(
					"No valid fields names " + firstName + ", and "
							+ lastName);
			}
			else if (_validRegex(position)) {
				throw new RuntimeException(
						"No valid field names " + position);
			}
			else if (_managerLocalService.getManager(managerIdPK) == null) {
				throw new NoSuchManagerException(
						"Not found manager with id " + managerIdPK);
			}
			else {
				// Process about positions available on company

				HashMap<String, Integer[]> positionsMap =
						HashMapBuilder.put(
								"Assoc Software Engineer",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4})
						).put(
								"SoftwareEngineer",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5})
						).put(
								"Senior Software Engineer", ArrayUtil.append(
										new Integer[]{1, 2, 3, 4, 5, 6})
						).put(
								"Team Leader Software Engineer",
								ArrayUtil.append(new Integer[]{1, 2, 3})
						).build();

				if (!positionsMap.containsKey(position)) {
					throw new RuntimeException(
							"That position not is available " + position);
				}
			}
		}
		catch (PortalException portalException) {
			if (portalException instanceof NoSuchManagerException) {
				throw new NoSuchManagerException(portalException);
			}
			else {
				throw new RuntimeException(portalException);
			}
		}

	}

	private boolean _validRegex(String content) {
		Pattern pattern = Pattern.compile("[A-Z]");

		Matcher matcher = pattern.matcher(content);

		return matcher.find();
	}

	private static final Log _log = LogFactoryUtil.getLog(
			EmployeeLocalServiceImpl.class);

	@Reference
	private EmployeePersistence _employeePersistence;

	@Reference
	private ManagerLocalService _managerLocalService;

}