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
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.EmployeeService;
import com.management.app.service.base.ManagerLocalServiceBaseImpl;

import com.management.app.service.persistence.ManagerPersistence;
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
			long groupId, long companyId, long employeeIdPK, Date modifiedDate,
			Date createDate, long mvccVersion)
		throws NoSuchEmployeeException {

        try {
			long managerId = CounterLocalServiceUtil.increment();

			_validate(employeeIdPK, groupId);

			Manager manager = managerPersistence.create(managerId);

			manager.setEmployeeIdPK(employeeIdPK);
			manager.setGroupId(groupId);
			manager.setCompanyId(companyId);
			manager.setMvccVersion(mvccVersion);
			manager.setCreateDate(new Date());
			manager.setModifiedDate(new Date());

			return managerPersistence.update(manager);
        }
		catch (PortalException portalException) {
            throw new NoSuchEmployeeException(portalException);
        }

	}

	@Override
	public Manager fetchManagerByFirstNameAndLastName(
			String firstName, String lastName) throws Exception {
//		List<Employee> employees = _employeeLocalService.getEmployees(-1, -1);
//
//		if (employees.isEmpty()) {
//			return null;
//		}
//
//		Manager manager = null;
//
//		for (Employee employee : employees) {
//			if (firstName.equals(employee.getFirstName()) &&
//					lastName.equals(employee.getLastName())) {
//
//				_log.debug("Returning manager by full name " +
//						employee.getFirstName() + StringPool.SPACE + employee.getLastName());
//
//				manager = managerPersistence.fetchBymanagerId(
//						employee.getManagerIdPK(), employee.getCompanyId());
//
//			}
//		}

		return null;
	}

	private void _validate(long employeeId, long managerId)
		throws PortalException {

//		try {
//			if (_employeeService.) == null) {
//				throw new NoSuchEmployeeException(
//						"No such employee with the primary key " + employeeId);
//			}
//		}
//		catch (PortalException portalException) {
//			throw new NoSuchEmployeeException(portalException.getMessage());
//		}

	}

	private static final Log _log = LogFactoryUtil.getLog(
			ManagerLocalServiceImpl.class);

//	@Reference
//	private EmployeeService _employeeService;

}