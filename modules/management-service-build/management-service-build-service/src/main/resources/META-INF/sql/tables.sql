create table Management_Employee (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	employeeId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	managerIdPK LONG,
	position VARCHAR(75) null,
	stateCode VARCHAR(75) null,
	status INTEGER,
	companyTime LONG
);

create table Management_Manager (
	mvccVersion LONG default 0 not null,
	uuid_ VARCHAR(75) null,
	managerId LONG not null primary key,
	groupId LONG,
	companyId LONG,
	createDate DATE null,
	modifiedDate DATE null,
	firstName VARCHAR(75) null,
	lastName VARCHAR(75) null,
	employeeIdPK LONG,
	position VARCHAR(75) null,
	stateCode VARCHAR(75) null,
	status INTEGER,
	companyTime INTEGER
);