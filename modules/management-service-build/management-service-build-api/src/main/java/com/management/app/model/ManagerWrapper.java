/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.model;

import com.liferay.exportimport.kernel.lar.StagedModelType;
import com.liferay.portal.kernel.model.ModelWrapper;
import com.liferay.portal.kernel.model.wrapper.BaseModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Manager}.
 * </p>
 *
 * @author Albert Cabral
 * @see Manager
 * @generated
 */
public class ManagerWrapper
	extends BaseModelWrapper<Manager>
	implements Manager, ModelWrapper<Manager> {

	public ManagerWrapper(Manager manager) {
		super(manager);
	}

	@Override
	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("mvccVersion", getMvccVersion());
		attributes.put("uuid", getUuid());
		attributes.put("managerId", getManagerId());
		attributes.put("groupId", getGroupId());
		attributes.put("companyId", getCompanyId());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("employeeIdPK", getEmployeeIdPK());

		return attributes;
	}

	@Override
	public void setModelAttributes(Map<String, Object> attributes) {
		Long mvccVersion = (Long)attributes.get("mvccVersion");

		if (mvccVersion != null) {
			setMvccVersion(mvccVersion);
		}

		String uuid = (String)attributes.get("uuid");

		if (uuid != null) {
			setUuid(uuid);
		}

		Long managerId = (Long)attributes.get("managerId");

		if (managerId != null) {
			setManagerId(managerId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		Long employeeIdPK = (Long)attributes.get("employeeIdPK");

		if (employeeIdPK != null) {
			setEmployeeIdPK(employeeIdPK);
		}
	}

	@Override
	public Manager cloneWithOriginalValues() {
		return wrap(model.cloneWithOriginalValues());
	}

	/**
	 * Returns the company ID of this manager.
	 *
	 * @return the company ID of this manager
	 */
	@Override
	public long getCompanyId() {
		return model.getCompanyId();
	}

	/**
	 * Returns the create date of this manager.
	 *
	 * @return the create date of this manager
	 */
	@Override
	public Date getCreateDate() {
		return model.getCreateDate();
	}

	/**
	 * Returns the employee ID pk of this manager.
	 *
	 * @return the employee ID pk of this manager
	 */
	@Override
	public long getEmployeeIdPK() {
		return model.getEmployeeIdPK();
	}

	/**
	 * Returns the group ID of this manager.
	 *
	 * @return the group ID of this manager
	 */
	@Override
	public long getGroupId() {
		return model.getGroupId();
	}

	/**
	 * Returns the manager ID of this manager.
	 *
	 * @return the manager ID of this manager
	 */
	@Override
	public long getManagerId() {
		return model.getManagerId();
	}

	/**
	 * Returns the modified date of this manager.
	 *
	 * @return the modified date of this manager
	 */
	@Override
	public Date getModifiedDate() {
		return model.getModifiedDate();
	}

	/**
	 * Returns the mvcc version of this manager.
	 *
	 * @return the mvcc version of this manager
	 */
	@Override
	public long getMvccVersion() {
		return model.getMvccVersion();
	}

	/**
	 * Returns the primary key of this manager.
	 *
	 * @return the primary key of this manager
	 */
	@Override
	public long getPrimaryKey() {
		return model.getPrimaryKey();
	}

	/**
	 * Returns the uuid of this manager.
	 *
	 * @return the uuid of this manager
	 */
	@Override
	public String getUuid() {
		return model.getUuid();
	}

	@Override
	public void persist() {
		model.persist();
	}

	/**
	 * Sets the company ID of this manager.
	 *
	 * @param companyId the company ID of this manager
	 */
	@Override
	public void setCompanyId(long companyId) {
		model.setCompanyId(companyId);
	}

	/**
	 * Sets the create date of this manager.
	 *
	 * @param createDate the create date of this manager
	 */
	@Override
	public void setCreateDate(Date createDate) {
		model.setCreateDate(createDate);
	}

	/**
	 * Sets the employee ID pk of this manager.
	 *
	 * @param employeeIdPK the employee ID pk of this manager
	 */
	@Override
	public void setEmployeeIdPK(long employeeIdPK) {
		model.setEmployeeIdPK(employeeIdPK);
	}

	/**
	 * Sets the group ID of this manager.
	 *
	 * @param groupId the group ID of this manager
	 */
	@Override
	public void setGroupId(long groupId) {
		model.setGroupId(groupId);
	}

	/**
	 * Sets the manager ID of this manager.
	 *
	 * @param managerId the manager ID of this manager
	 */
	@Override
	public void setManagerId(long managerId) {
		model.setManagerId(managerId);
	}

	/**
	 * Sets the modified date of this manager.
	 *
	 * @param modifiedDate the modified date of this manager
	 */
	@Override
	public void setModifiedDate(Date modifiedDate) {
		model.setModifiedDate(modifiedDate);
	}

	/**
	 * Sets the mvcc version of this manager.
	 *
	 * @param mvccVersion the mvcc version of this manager
	 */
	@Override
	public void setMvccVersion(long mvccVersion) {
		model.setMvccVersion(mvccVersion);
	}

	/**
	 * Sets the primary key of this manager.
	 *
	 * @param primaryKey the primary key of this manager
	 */
	@Override
	public void setPrimaryKey(long primaryKey) {
		model.setPrimaryKey(primaryKey);
	}

	/**
	 * Sets the uuid of this manager.
	 *
	 * @param uuid the uuid of this manager
	 */
	@Override
	public void setUuid(String uuid) {
		model.setUuid(uuid);
	}

	@Override
	public String toXmlString() {
		return model.toXmlString();
	}

	@Override
	public StagedModelType getStagedModelType() {
		return model.getStagedModelType();
	}

	@Override
	protected ManagerWrapper wrap(Manager manager) {
		return new ManagerWrapper(manager);
	}

}