/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
package com.management.app.exception;

import com.liferay.portal.kernel.exception.PortalException;

/**
 * @author Albert Cabral
 */
public class EmployeeDuplicatedNameException extends PortalException {

	public EmployeeDuplicatedNameException() {
	}

	public EmployeeDuplicatedNameException(String msg) {
		super(msg);
	}

	public EmployeeDuplicatedNameException(String msg, Throwable throwable) {
		super(msg, throwable);
	}

	public EmployeeDuplicatedNameException(Throwable throwable) {
		super(throwable);
	}

}