/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.EmployeeLocalServiceUtil;
import com.management.app.service.base.ManagerLocalServiceBaseImpl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;
import java.util.List;

/**
 * @author Albert Cabral
 */
@Component(
	property = "model.class.name=com.management.app.model.Manager",
	service = AopService.class
)
public class ManagerLocalServiceImpl extends ManagerLocalServiceBaseImpl {

	@Override
	public Manager createManager(
			long groupId, long companyId, long employeeIdPK, long mvccVersion)
		throws NoSuchEmployeeException {

		long managerId = CounterLocalServiceUtil.increment();

		Manager manager = managerPersistence.create(managerId);

		manager.setEmployeeIdPK(employeeIdPK);
		manager.setGroupId(groupId);
		manager.setCompanyId(companyId);
		manager.setMvccVersion(mvccVersion);
		manager.setCreateDate(new Date());
		manager.setModifiedDate(new Date());

		return managerPersistence.update(manager);

	}

	@Override
	public Manager fetchManagerByFirstNameAndLastName(
			String firstName, String lastName) throws Exception {
		List<Employee> employees = EmployeeLocalServiceUtil.getEmployees(-1, -1);

		if (employees.isEmpty()) {
			return null;
		}

		for (Employee employee : employees) {
			if (firstName.equals(employee.getFirstName()) &&
					lastName.equals(employee.getLastName())) {

				_log.debug("Returning manager by full name " +
						employee.getFirstName() + StringPool.SPACE + employee.getLastName());

				return managerPersistence.fetchByE_M(
						employee.getEmployeeId(), employee.getCompanyId());

			}
		}

		return null;
	}

	private void _validate(long employeeId) throws PortalException {
		try {
			Employee employee = EmployeeLocalServiceUtil.fetchEmployee(employeeId);

			if (employee == null) {
				throw new NoSuchEmployeeException(
						"No such employee with the primary key " + employeeId);
			}
		}
		catch (PortalException portalException) {
			throw new NoSuchEmployeeException(portalException.getMessage());
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(
			ManagerLocalServiceImpl.class);

}