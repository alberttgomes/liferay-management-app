/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.management.app.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Albert Cabral
 */
public class ManagerException extends PortalException {

	public ManagerException() {
	}

	public ManagerException(String msg) {
		super(msg);
	}

	public ManagerException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public ManagerException(Throwable throwable) {
		super(throwable);
	}

}