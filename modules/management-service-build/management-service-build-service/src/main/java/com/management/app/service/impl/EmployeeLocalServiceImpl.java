/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.StringBundler;

import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.exception.NoSuchManagerException;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.ManagerLocalService;
import com.management.app.service.base.EmployeeLocalServiceBaseImpl;
import com.management.app.service.util.EmployeeStatusConstant;

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
			String firstName, String lastName, String department, String position,
			int level, String stateCode, int status, long managerIdPK,
			boolean isManager, User user)
		throws NoSuchManagerException {

		long employeeId = CounterLocalServiceUtil.increment();

		_validate(firstName, lastName, position, level, department);

		return _addEmployee(
				firstName, lastName, department, position, level, stateCode,
				managerIdPK, employeeId, isManager, user);
	}

	@Override
	public Employee deleteEmployee(long employeeId) throws NoSuchEmployeeException {
		if (employeePersistence.findByEmployeeId(employeeId) == null) {
			throw new NoSuchEmployeeException(
					"No such employee with id: " + employeeId);
		}
		else {
			_log.debug("Deleting employee with id: " + employeeId);

			return employeePersistence.remove(employeeId);
		}
	}

	@Override
	public List<Employee> getEmployeesByManagerIdAndPermission(
			long managerIdPK, long companyId, boolean hasPermission)
		throws NoSuchEmployeeException, NoSuchManagerException {

		try {
			if (managerIdPK == -1) {
				_log.warn(StringBundler.concat("Unable to get employees ",
						"with the associated ", "manager id " + managerIdPK));

				return null;
			}

			Manager manager = _managerLocalService.getManager(managerIdPK);

			if (manager == null) {
				throw new NoSuchManagerException(
						"No manager found for id: " + managerIdPK);
			}

			List<Employee> employees = employeePersistence.findAll();

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

	@Override
	public Manager employeePromoting(
			String position, long userId, String department, int level,
			long employeeId) throws PortalException {

		try {
			User user = userLocalService.fetchUser(userId);

			if (user == null) {
				throw new NoSuchEmployeeException(
						"No user site available found for id: " + userId);
			}

			_log.debug("Are promoting by " + user.getFullName());

			Employee employee = super.getEmployee(employeeId);

			employee.setIsManager(true);

			employee.setDepartment(department);
			employee.setLevel(level);
			employee.setPosition(position);

			employeePersistence.update(employee);

			Manager manager = _managerLocalService.createManager(
					CounterLocalServiceUtil.increment());

			manager.setCreateDate(new Date());
			manager.setCompanyId(employee.getCompanyId());
			manager.setEmployeeIdPK(employee.getEmployeeId());
			manager.setGroupId(employee.getGroupId());
			manager.setPrimaryKey(employee.getPrimaryKey());
			manager.setModifiedDate(new Date());
			manager.setMvccVersion(0);

			return _managerLocalService.updateManager(manager);

		}
		catch (NoSuchEmployeeException noSuchEmployeeException) {
			throw new NoSuchEmployeeException(
					"Unable to promote employee with the id " +
							employeeId, noSuchEmployeeException);
		}

	}

	private Employee _addEmployee(
			String firstName, String lastName, String department, String position,
			int level, String stateCode, long managerIdPK, long employeeId,
			boolean isManager, User user) {

		Employee employee = employeePersistence.create(employeeId);

		employee.setCreateDate(new Date());
		employee.setFirstName(firstName);
		employee.setLastName(lastName);
		employee.setDepartment(department);
		employee.setLevel(level);
		employee.setManagerIdPK(managerIdPK);
		employee.setPosition(position);
		employee.setPrimaryKey(employeeId);
		employee.setStateCode(stateCode);
		employee.setStatus(EmployeeStatusConstant.ACTIVE);
		employee.setGroupId(user.getGroupId());
		employee.setUserId(user.getUserId());

		long companyId = CompanyThreadLocal.getCompanyId();

		employee.setCompanyId(companyId);
		employee.setMvccVersion(0);

		if (isManager) {
			_log.debug("New employee also is a manager");

			Manager manager = _managerLocalService.createManager(
					CounterLocalServiceUtil.increment());

			manager.setCompanyId(companyId);
			manager.setEmployeeIdPK(employeeId);
			manager.setGroupId(user.getGroupId());
			manager.setMvccVersion(0);
			manager.setCreateDate(new Date());
			manager.setModifiedDate(new Date());

			_managerLocalService.updateManager(manager);
		}

		_log.info("Create employee " + employee.getFirstName());

		return employeePersistence.update(employee);
	}

	private void _validate(
			String firstName, String lastName, String position,
			int level, String department)
		throws NoSuchManagerException {

		try {
			if (!_validRegex(firstName) && !_validRegex(lastName)) {
				throw new RuntimeException(
					"No valid fields names " + firstName + ", and " +
							lastName);
			}
			else if (!_validRegex(position) && _validRegex(department)) {
				throw new RuntimeException(
						"No valid field names " + position + ", and " +
								department);
			}
			else {
				// Validate job available positions

				Map<String, String> departmentMap = HashMapBuilder.put(
						"department", department).build();

				if (departmentMap.isEmpty()) {
					throw new RuntimeException(
							"No available department identifier " +
									department);
				}

				JSONObject jsonObject = _departmentsJSONObject();

				jsonObject = (JSONObject)
						jsonObject.get(departmentMap.get("department"));

				if (jsonObject.get(position) == null) {
					throw new RuntimeException(
							"That position not is available " + position);
				}

				JSONObject levelsByPosition = (JSONObject) jsonObject.get(position);

				Integer[] levels = (Integer[]) levelsByPosition.get("level");

				boolean available = false;

				for (int l : levels) {
					if (l == level) {
						available = true;

						break;
					}
				}

				if (!available) {
					throw new RuntimeException("No available level " + level);
				}
			}
		}
		catch (RuntimeException runtimeException) {
			throw new NoSuchManagerException(runtimeException);
		}

	}

	private JSONObject _departmentsJSONObject() {
		return JSONUtil.put(
				"engineer",
				JSONUtil.put(
						"Assoc Software Engineer",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4})
						)
				).put(
						"Software Engineer",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5})
						)
				).put(
						"Senior Software Engineer",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5, 6})
						)
				).put(
						"Team Leader Software Engineer",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3})
						)
				)
		).put(
				"general",
				JSONUtil.put(
						"Marketing",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3})
						)
				).put(
						"Sales Product",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3})
						)
				).put(
						"Operations",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4})
						)
				).put(
						"Product Design",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3})
						)
				).put(
						"Human Resources",
						JSONUtil.put(
								"level",
								ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5})
						)
				)
		);
	}

	private boolean _validRegex(String content) {
		Pattern pattern = Pattern.compile("[A-z]");

		Matcher matcher = pattern.matcher(content);

        return matcher.find();
    }

	private static final Log _log = LogFactoryUtil.getLog(
			EmployeeLocalServiceImpl.class);

	@Reference
	private ManagerLocalService _managerLocalService;

}