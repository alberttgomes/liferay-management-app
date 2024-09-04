/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.model;

import com.liferay.portal.kernel.annotation.ImplementationClassName;
import com.liferay.portal.kernel.model.PersistedModel;
import com.liferay.portal.kernel.util.Accessor;

import org.osgi.annotation.versioning.ProviderType;

/**
 * The extended model interface for the Manager service. Represents a row in the &quot;Management_Manager&quot; database table, with each column mapped to a property of this class.
 *
 * @author Albert Cabral
 * @see ManagerModel
 * @generated
 */
@ImplementationClassName("com.management.app.model.impl.ManagerImpl")
@ProviderType
public interface Manager extends ManagerModel, PersistedModel {

	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this interface directly. Add methods to <code>com.management.app.model.impl.ManagerImpl</code> and rerun ServiceBuilder to automatically copy the method declarations to this interface.
	 */
	public static final Accessor<Manager, Long> MANAGER_ID_ACCESSOR =
		new Accessor<Manager, Long>() {

			@Override
			public Long get(Manager manager) {
				return manager.getManagerId();
			}

			@Override
			public Class<Long> getAttributeClass() {
				return Long.class;
			}

			@Override
			public Class<Manager> getTypeClass() {
				return Manager.class;
			}

		};

}