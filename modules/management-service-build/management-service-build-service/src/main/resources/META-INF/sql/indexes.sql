create index IX_F3467A55 on Management_Employee (companyId, employeeId);
create index IX_810386C8 on Management_Employee (companyId, firstName[$COLUMN_LENGTH:75$], lastName[$COLUMN_LENGTH:75$]);
create index IX_58891756 on Management_Employee (status);
create unique index IX_E8C65DE6 on Management_Employee (uuid_[$COLUMN_LENGTH:75$], groupId);

create index IX_D4A3CD8F on Management_Manager (companyId, firstName[$COLUMN_LENGTH:75$], lastName[$COLUMN_LENGTH:75$]);
create index IX_DE492A27 on Management_Manager (companyId, managerId);
create unique index IX_904312AD on Management_Manager (uuid_[$COLUMN_LENGTH:75$], groupId);