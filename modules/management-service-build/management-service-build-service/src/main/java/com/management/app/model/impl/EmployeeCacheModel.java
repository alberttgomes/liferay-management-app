/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.model.impl;

import com.liferay.petra.lang.HashUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.model.CacheModel;
import com.liferay.portal.kernel.model.MVCCModel;

import com.management.app.model.Employee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

import java.util.Date;

/**
 * The cache model class for representing Employee in entity cache.
 *
 * @author Albert Cabral
 * @generated
 */
public class EmployeeCacheModel
	implements CacheModel<Employee>, Externalizable, MVCCModel {

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof EmployeeCacheModel)) {
			return false;
		}

		EmployeeCacheModel employeeCacheModel = (EmployeeCacheModel)object;

		if ((employeeId == employeeCacheModel.employeeId) &&
			(mvccVersion == employeeCacheModel.mvccVersion)) {

			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		int hashCode = HashUtil.hash(0, employeeId);

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
		StringBundler sb = new StringBundler(33);

		sb.append("{mvccVersion=");
		sb.append(mvccVersion);
		sb.append(", uuid=");
		sb.append(uuid);
		sb.append(", employeeId=");
		sb.append(employeeId);
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
		sb.append(", position=");
		sb.append(position);
		sb.append(", isManager=");
		sb.append(isManager);
		sb.append(", level=");
		sb.append(level);
		sb.append(", managerIdPK=");
		sb.append(managerIdPK);
		sb.append(", stateCode=");
		sb.append(stateCode);
		sb.append(", status=");
		sb.append(status);
		sb.append(", userId=");
		sb.append(userId);
		sb.append("}");

		return sb.toString();
	}

	@Override
	public Employee toEntityModel() {
		EmployeeImpl employeeImpl = new EmployeeImpl();

		employeeImpl.setMvccVersion(mvccVersion);

		if (uuid == null) {
			employeeImpl.setUuid("");
		}
		else {
			employeeImpl.setUuid(uuid);
		}

		employeeImpl.setEmployeeId(employeeId);
		employeeImpl.setGroupId(groupId);
		employeeImpl.setCompanyId(companyId);

		if (createDate == Long.MIN_VALUE) {
			employeeImpl.setCreateDate(null);
		}
		else {
			employeeImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			employeeImpl.setModifiedDate(null);
		}
		else {
			employeeImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (firstName == null) {
			employeeImpl.setFirstName("");
		}
		else {
			employeeImpl.setFirstName(firstName);
		}

		if (lastName == null) {
			employeeImpl.setLastName("");
		}
		else {
			employeeImpl.setLastName(lastName);
		}

		if (position == null) {
			employeeImpl.setPosition("");
		}
		else {
			employeeImpl.setPosition(position);
		}

		employeeImpl.setIsManager(isManager);
		employeeImpl.setLevel(level);
		employeeImpl.setManagerIdPK(managerIdPK);

		if (stateCode == null) {
			employeeImpl.setStateCode("");
		}
		else {
			employeeImpl.setStateCode(stateCode);
		}

		employeeImpl.setStatus(status);
		employeeImpl.setUserId(userId);

		employeeImpl.resetOriginalValues();

		return employeeImpl;
	}

	@Override
	public void readExternal(ObjectInput objectInput) throws IOException {
		mvccVersion = objectInput.readLong();
		uuid = objectInput.readUTF();

		employeeId = objectInput.readLong();

		groupId = objectInput.readLong();

		companyId = objectInput.readLong();
		createDate = objectInput.readLong();
		modifiedDate = objectInput.readLong();
		firstName = objectInput.readUTF();
		lastName = objectInput.readUTF();
		position = objectInput.readUTF();

		isManager = objectInput.readBoolean();

		level = objectInput.readInt();

		managerIdPK = objectInput.readLong();
		stateCode = objectInput.readUTF();

		status = objectInput.readInt();

		userId = objectInput.readLong();
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

		objectOutput.writeLong(employeeId);

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

		if (position == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(position);
		}

		objectOutput.writeBoolean(isManager);

		objectOutput.writeInt(level);

		objectOutput.writeLong(managerIdPK);

		if (stateCode == null) {
			objectOutput.writeUTF("");
		}
		else {
			objectOutput.writeUTF(stateCode);
		}

		objectOutput.writeInt(status);

		objectOutput.writeLong(userId);
	}

	public long mvccVersion;
	public String uuid;
	public long employeeId;
	public long groupId;
	public long companyId;
	public long createDate;
	public long modifiedDate;
	public String firstName;
	public String lastName;
	public String position;
	public boolean isManager;
	public int level;
	public long managerIdPK;
	public String stateCode;
	public int status;
	public long userId;

}