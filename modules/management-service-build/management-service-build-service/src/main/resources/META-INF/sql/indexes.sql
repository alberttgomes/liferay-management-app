create index IX_45B8C9C1 on Management_Employee (department[$COLUMN_LENGTH:75$], position[$COLUMN_LENGTH:75$], level);
create index IX_6D9670FB on Management_Employee (employeeId, companyId);
create index IX_7F563AF3 on Management_Employee (employeeId, status);
create index IX_F525B710 on Management_Employee (firstName[$COLUMN_LENGTH:75$], lastName[$COLUMN_LENGTH:75$]);
create index IX_4D34497D on Management_Employee (status, managerIdPK);
create unique index IX_E8C65DE6 on Management_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_DD4C4091 on Management_Manager (companyId);
create index IX_C39BD5C7 on Management_Manager (managerId, companyId);
create index IX_19AD5719 on Management_Manager (managerId, employeeIdPK);
create unique index IX_904312AD on Management_Manager (uuid_[$COLUMN_LENGTH:75$], groupId);