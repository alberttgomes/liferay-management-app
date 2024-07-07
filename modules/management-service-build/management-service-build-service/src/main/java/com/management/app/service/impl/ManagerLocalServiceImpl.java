/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.portal.aop.AopService;

import com.liferay.portal.kernel.exception.PortalException;
import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.model.Manager;
import com.management.app.model.impl.ManagerImpl;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.base.ManagerLocalServiceBaseImpl;

import com.management.app.service.persistence.ManagerPersistence;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.util.Date;

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

			Manager manager = _managerPersistence.create(managerId);

			manager.setEmployeeIdPK(employeeIdPK);
			manager.setGroupId(groupId);
			manager.setCompanyId(companyId);
			manager.setMvccVersion(mvccVersion);
			manager.setCreateDate(new Date());
			manager.setModifiedDate(new Date());

			return _managerPersistence.update(manager);
        }
		catch (PortalException portalException) {
            throw new NoSuchEmployeeException(portalException);
        }

	}

	private void _validate(long employeeId, long managerId)
		throws PortalException, NoSuchEmployeeException {

		try {
			if (_employeeLocalService.getEmployee(employeeId) == null) {
				throw new NoSuchEmployeeException(
						"No such employee with the primary key " + employeeId);
			}
		}
		catch (NoSuchEmployeeException noSuchEmployeeException) {
			throw new NoSuchEmployeeException(
					noSuchEmployeeException.getMessage());
		}

	}

	@Reference
	private EmployeeLocalService _employeeLocalService;

	@Reference
	private ManagerPersistence _managerPersistence;

}