/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import com.management.app.model.Manager;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Manager in entity cache.
 *
 * @author Albert Cabral
 * @generated
 */
public class ManagerCacheModel
	implements CacheModel<Manager>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof ManagerCacheModel)) {
			return false;
		}

		ManagerCacheModel managerCacheModel = (ManagerCacheModel)object;

		if ((managerId == managerCacheModel.managerId) &&
			(mvccVersion == managerCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, managerId);

		return HashUtil.hash(hashCode, mvccVersion);
	}

	@Override
	public long getMvccVersion() {
		return mvccVersion;
	}

	@Override
	public void setMvccVersion(long mvccVersion) {
		this.mvccVersion = mvccVersion;
	}

	@Override
	public String toString() {
		StringBundler sb = new StringBundler(29);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", managerId=");
		sb.append(managerId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", firstName=");
		sb.append(firstName);
		sb.append(", lastName=");
		sb.append(lastName);
		sb.append(", employeeIdPK=");
		sb.append(employeeIdPK);
		sb.append(", position=");
		sb.append(position);
		sb.append(", stateCode=");
		sb.append(stateCode);
		sb.append(", status=");
		sb.append(status);
		sb.append(", companyTime=");
		sb.append(companyTime);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Manager toEntityModel() {
		ManagerImpl managerImpl = new ManagerImpl();

		managerImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			managerImpl.setUuid("");
		}
		else {
			managerImpl.setUuid(uuid);
		}

		managerImpl.setManagerId(managerId);
		managerImpl.setGroupId(groupId);
		managerImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			managerImpl.setCreateDate(null);
		}
		else {
			managerImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			managerImpl.setModifiedDate(null);
		}
		else {
			managerImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			managerImpl.setFirstName("");
		}
		else {
			managerImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			managerImpl.setLastName("");
		}
		else {
			managerImpl.setLastName(lastName);
		}

		managerImpl.setEmployeeIdPK(employeeIdPK);

		if (position == null) {
			managerImpl.setPosition("");
		}
		else {
			managerImpl.setPosition(position);
		}

		if (stateCode == null) {
			managerImpl.setStateCode("");
		}
		else {
			managerImpl.setStateCode(stateCode);
		}

		managerImpl.setStatus(status);
		managerImpl.setCompanyTime(companyTime);

		managerImpl.resetOriginalValues();

		return managerImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		managerId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();

		employeeIdPK = objectInput.readLong();
		position = objectInput.readUTF();
		stateCode = objectInput.readUTF();

		status = objectInput.readInt();

		companyTime = objectInput.readInt();
	}

	@Override
	public void writeExternal(ObjectOutput objectOutput) throws IOException {
		objectOutput.writeLong(mvccVersion);

		if (uuid == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(uuid);
		}

		objectOutput.writeLong(managerId);

		objectOutput.writeLong(groupId);

		objectOutput.writeLong(companyId);
		objectOutput.writeLong(createDate);
		objectOutput.writeLong(modifiedDate);

		if (firstName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(firstName);
		}

		if (lastName == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(lastName);
		}

		objectOutput.writeLong(employeeIdPK);

		if (position == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(position);
		}

		if (stateCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stateCode);
		}

		objectOutput.writeInt(status);

		objectOutput.writeInt(companyTime);
	}

	public long mvccVersion;
	public String uuid;
	public long managerId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String lastName;
	public long employeeIdPK;
	public String position;
	public String stateCode;
	public int status;
	public int companyTime;

}