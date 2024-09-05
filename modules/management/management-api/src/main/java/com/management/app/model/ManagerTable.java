/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.model;

import com.liferay.petra.sql.dsl.Column;
import com.liferay.petra.sql.dsl.base.BaseTable;

import java.sql.Types;

import java.util.Date;

/**
 * The table class for the &quot;Management_Manager&quot; database table.
 *
 * @author Albert Cabral
 * @see Manager
 * @generated
 */
public class ManagerTable extends BaseTable<ManagerTable> {

	public static final ManagerTable INSTANCE = new ManagerTable();

	public final Column<ManagerTable, Long> mvccVersion = createColumn(
		"mvccVersion", Long.class, Types.BIGINT, Column.FLAG_NULLITY);
	public final Column<ManagerTable, String> uuid = createColumn(
		"uuid_", String.class, Types.VARCHAR, Column.FLAG_DEFAULT);
	public final Column<ManagerTable, Long> managerId = createColumn(
		"managerId", Long.class, Types.BIGINT, Column.FLAG_PRIMARY);
	public final Column<ManagerTable, Long> groupId = createColumn(
		"groupId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ManagerTable, Long> companyId = createColumn(
		"companyId", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);
	public final Column<ManagerTable, Date> createDate = createColumn(
		"createDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ManagerTable, Date> modifiedDate = createColumn(
		"modifiedDate", Date.class, Types.TIMESTAMP, Column.FLAG_DEFAULT);
	public final Column<ManagerTable, Long> employeeIdPK = createColumn(
		"employeeIdPK", Long.class, Types.BIGINT, Column.FLAG_DEFAULT);

	private ManagerTable() {
		super("Management_Manager", ManagerTable::new);
	}

}