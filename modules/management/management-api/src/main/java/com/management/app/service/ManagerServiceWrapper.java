/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service;

import com.liferay.portal.kernel.service.ServiceWrapper;

/**
 * Provides a wrapper for {@link ManagerService}.
 *
 * @author Albert Cabral
 * @see ManagerService
 * @generated
 */
public class ManagerServiceWrapper
	implements ManagerService, ServiceWrapper<ManagerService> {

	public ManagerServiceWrapper() {
		this(null);
	}

	public ManagerServiceWrapper(ManagerService managerService) {
		_managerService = managerService;
	}

	/**
	 * Returns the OSGi service identifier.
	 *
	 * @return the OSGi service identifier
	 */
	@Override
	public String getOSGiServiceIdentifier() {
		return _managerService.getOSGiServiceIdentifier();
	}

	@Override
	public ManagerService getWrappedService() {
		return _managerService;
	}

	@Override
	public void setWrappedService(ManagerService managerService) {
		_managerService = managerService;
	}

	private ManagerService _managerService;

}