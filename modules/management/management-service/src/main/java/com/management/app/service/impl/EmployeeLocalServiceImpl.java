/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.search.BaseModelSearchResult;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.ClassNameLocalService;
import com.liferay.portal.kernel.service.ClassNameLocalServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.HashMapBuilder;
import com.liferay.portal.kernel.util.LinkedHashMapBuilder;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.exception.NoSuchManagerException;
import com.management.app.constants.EmployeeStructureConstants;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.ManagerLocalService;
import com.management.app.service.base.EmployeeLocalServiceBaseImpl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = "model.class.name=com.management.app.model.Employee",
    service = AopService.class
)
public class EmployeeLocalServiceImpl extends EmployeeLocalServiceBaseImpl {

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Employee addEmployee(
            String firstName, String lastName, String department, String position,
            int level, String stateCode, int status, long managerId,
            boolean isManager, User user)
        throws PortalException {

        long employeeId = CounterLocalServiceUtil.increment();

        _validate(firstName, lastName, position, level, department);

        return _addEmployee(
                firstName, lastName, department, position,
                level, stateCode, employeeId, isManager, user, managerId);
    }

    @Indexable(type = IndexableType.DELETE)
    @Override
    public Employee deleteEmployee(long employeeId) throws NoSuchEmployeeException {
        if (employeePersistence.findByEmployeeId(employeeId).isEmpty() ||
                employeePersistence.findByEmployeeId(employeeId) == null) {

            throw new NoSuchEmployeeException(
                    "No such employee with id: " + employeeId);
        }
        else {
            return employeePersistence.remove(employeeId);
        }
    }

    @Override
    public Employee getEmployee(long employeeId) {
        return employeePersistence.fetchByPrimaryKey(employeeId);
    }

    @Override
    public List<Employee> getAllEmployeeByManager(
            long employeeId, long companyId, boolean hasPermission)
        throws NoSuchEmployeeException, NoSuchManagerException {

        try {
            Manager manager = _managerLocalService.findByCompanyIdAndEmployeeId(
                companyId, employeeId);

            if (manager == null) {
                throw new NoSuchManagerException(
                        "No manager found for id: " + employeeId);
            }

            List<Employee> employees = employeePersistence.findAll();

            List<Employee> associatedEmployees = new ArrayList<>();

            if (employees.isEmpty()) {
                throw new NoSuchEmployeeException(
                        "Associated employees list is empty");
            }

            for (Employee employee : employees) {
                if (employee.getManagerIdFK() == employeeId) {
                    associatedEmployees.add(employee);
                }
            }

            return associatedEmployees;
        }
        catch (RuntimeException | PortalException runtimeException) {
            if (runtimeException instanceof NoSuchEmployeeException) {
                throw new NoSuchEmployeeException(runtimeException);
            }
            else if (runtimeException instanceof NoSuchManagerException) {
                throw new NoSuchManagerException(runtimeException);
            }
            else {
                throw new RuntimeException(runtimeException);
            }
        }

    }

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Employee employeePromoting(
            String newPosition, long userId, String department, int newLevel,
            long employeeId, boolean isManager, boolean betweenLevels)
        throws PortalException {

        try {
            User user = userLocalService.fetchUser(userId);

            if (user == null || !user.isActive()) {
                throw new RuntimeException(
                        "Operation not allowed by the user id " + userId);
            }

            Employee employee = getEmployee(employeeId);

            if (employee == null) {
                throw new RuntimeException(
                        "No such employee with id " + employeeId);
            }

            _validatePromotion(
                    betweenLevels, department, employee.getFirstName(),
                    employee.getLastName(), newLevel, employee.getLevel(),
                    newPosition, employee.getPosition());

            employee.setDepartment(department);
            employee.setLevel(newLevel);
            employee.setPosition(newPosition);
            employee.setModifiedDate(new Date());

            employee = employeePersistence.update(employee);

            if (isManager) {
                _createManager(
                        employee.getCompanyId(),
                        employee.getEmployeeId(),
                        employee.getGroupId(),
                        employee.getMvccVersion());
            }

            User userEmployee = _userLocalService.fetchUser(
                    employee.getUserId());

            if (userEmployee != null) {
                userEmployee.setJobTitle(employee.getPosition());

                _userLocalService.updateUser(userEmployee);
            }
            else {
                if (_log.isWarnEnabled()) {
                    _log.warn(StringBundler.concat(
                            "Cannot update user's job title ",
                            "with the primary key ", employee.getUserId()));
                }
            }

            return employee;
        }
        catch (RuntimeException runtimeException) {
            throw new NoSuchEmployeeException(
                    "Unable to promote employee with the id " +
                            employeeId, runtimeException);
        }

    }

    @Override
    public Employee fetchEmployeeByUserId(long companyId, long userId) {
        return employeePersistence.fetchByC_U(companyId, userId, true);
    }

    @Override
    public BaseModelSearchResult<Employee> searchEmployees(
            long employeeId, String firstName, String department,
            int start, int end, Sort sort)
        throws PortalException {

        SearchContext searchContext = _buildSearchContext(
                employeeId, firstName, department, end, start, sort);

        return searchEmployees(searchContext);
    }

    @Override
    public BaseModelSearchResult<Employee> searchEmployees(
            long companyId, String className, String keywords,
            LinkedHashMap<String, Object> params, int start, int end, Sort sort)
        throws PortalException {

        SearchContext searchContext = _buildSearchContext(
                companyId, className, keywords, params, start, end, sort);

        return searchEmployees(searchContext);
    }

    @Override
    public BaseModelSearchResult<Employee>
        searchEmployees(SearchContext searchContext) throws PortalException {

        try {
            Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                    Employee.class);

            Hits hits = indexer.search(searchContext);

            return new BaseModelSearchResult<>(
                    _getEmployees(hits), hits.getLength());
        }
        catch (Exception exception) {
            throw new SearchException(
                    "Unable to found indexed employees ",
                            exception);
        }
    }

    @Indexable(type = IndexableType.REINDEX)
    @Override
    public Employee updateEmployee(
            String firstName, String lastName, String department,
            long employeeId, String stateCode, boolean isManager,
            long userId)
        throws PortalException {

        Employee employee = getEmployee(employeeId);

        if (Objects.isNull(employee)) {
            throw new NoSuchEmployeeException(
                    "No such employee with primary key " + employeeId);
        }

        _validate(
                firstName, lastName, employee.getPosition(),
                employee.getLevel(), department);

        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(department);
        employee.setIsManager(isManager);
        employee.setModifiedDate(new Date());
        employee.setStateCode(stateCode);
        employee.setUserId(userId);

        return employeePersistence.update(employee);
    }

    @Indexable(type = IndexableType.REINDEX)
    private Employee _addEmployee(
            String firstName, String lastName, String department, String position,
            int level, String stateCode, long employeeId, boolean isManager,
            User creatorUser, long managerId)
        throws PortalException {

        Employee employee = employeePersistence.create(employeeId);

        employee.setCreateDate(new Date());
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setDepartment(department);
        employee.setLevel(level);
        employee.setPosition(position);
        employee.setPrimaryKey(employeeId);
        employee.setStateCode(stateCode);
        employee.setStatus(WorkflowConstants.STATUS_APPROVED);
        employee.setGroupId(creatorUser.getGroupId());
        employee.setMvccVersion(0);

        if (isManager) {
            _createManager(
                employee.getCompanyId(), employee.getEmployeeId(),
                employee.getGroupId(), employee.getMvccVersion());
        }

        String domain = creatorUser.getEmailAddress().split("@")[1];

        String emailAddress = _createEmailAddressDomain(
            employee.getFirstName(), employee.getLastName(),
            Objects.isNull(domain) ? "management.com" : domain);

        String userScreenName =
            StringBundler.concat(
                employee.getFirstName(), StringPool.SPACE,
                employee.getLastName());

        int birthdayMonth = Calendar.SEPTEMBER;
        int birthdayDay = 19;
        int birthdayYear = 1995;

        ServiceContext serviceContext = _buildServiceContext(
                creatorUser.getUserId(), employee.getEmployeeId(),
                employee.getGroupId());

        User employeeUser = _userLocalService.addUser(
            creatorUser.getUserId(), employee.getCompanyId(), false, "batman",
            "batman", true, userScreenName, emailAddress,
            creatorUser.getLocale(), employee.getFirstName(), null, employee.getLastName(),
            0L, 0L, true, birthdayMonth,
            birthdayDay, birthdayYear, employee.getPosition(), UserConstants.TYPE_REGULAR,
            null, null, null, null,
            true, serviceContext);

        employeeUser.setUuid(employee.getUuid());

        _userLocalService.updateUser(employeeUser);

        employee.setUserId(employeeUser.getUserId());

        Manager manager = _managerLocalService.findByCompanyIdAndEmployeeId(
                employee.getCompanyId(), managerId);

        if (Objects.isNull(manager)) {
            employee.setManagerIdFK(creatorUser.getUserId());

            if (_log.isWarnEnabled()) {
                _log.warn(
                    StringBundler.concat(
                "Manager id for the Employee ",
                        employee.getEmployeeId(),
                        " was set with creatorUserId because not exists",
                        " employee by company and user ",
                        employee.getCompanyId(), creatorUser.getUserId()));
            }
        }

        employee.setManagerIdFK(manager.getEmployeeIdPK());

        employee = employeePersistence.update(employee);

        if (_log.isDebugEnabled()) {
            _log.debug("Create employee " + employee.getFirstName());
        }

        return employee;
    }

    private SearchContext _buildSearchContext(
            long companyId, String className, String keywords,
            LinkedHashMap<String, Object> params, int start, int end, Sort sort) {

        SearchContext searchContext = new SearchContext();

        searchContext.setAttributes(
            HashMapBuilder.<String, Serializable>put(
                Field.CLASS_NAME_ID,
                ClassNameLocalServiceUtil.getClassNameId(className)
            ).put(
                "department", keywords
            ).put(
                "params",
                LinkedHashMapBuilder.<String, Object>put(
                        "keywords", keywords
                ).build()
            ).put(
                "position", keywords
            ).build());

        searchContext.setCompanyId(companyId);
        searchContext.setEnd(end);

        if (Validator.isNotNull(keywords)) {
            searchContext.setKeywords(keywords);
        }

        if (sort != null) {
            searchContext.setSorts(sort);
        }

        searchContext.setStart(start);

        QueryConfig queryConfig = searchContext.getQueryConfig();

        queryConfig.setHighlightEnabled(false);
        queryConfig.setScoreEnabled(false);

        return searchContext;
    }

    private SearchContext _buildSearchContext(
            long employeeId, String firstName, String department,
            int start, int end, Sort sort)
        throws PortalException {

        Employee employee = employeePersistence.findByPrimaryKey(
                employeeId);

        SearchContext searchContext = new SearchContext();

        searchContext.setAndSearch(true);
        searchContext.setAttributes(
            HashMapBuilder.<String, Serializable>put(
                Field.CLASS_NAME_ID,
                _classNameLocalService.getClassNameId(Employee.class)
            ).put(
                    "employeeId", employee.getEmployeeId()
            ).put(
                    "department", employee.getDepartment()
            ).put(
                    "firstName", employee.getFirstName()
            ).put(
                    "position", employee.getPosition()
            ).build());
        searchContext.setCompanyId(employee.getCompanyId());
        searchContext.setEnd(end);
        searchContext.setGroupIds(new long[] {employee.getGroupId()});
        searchContext.setSorts(sort);
        searchContext.setStart(start);

        return searchContext;
    }

    private ServiceContext _buildServiceContext(
            long employeeId, long groupId, long userId) {

        Map<String, Serializable> attributes =
            HashMapBuilder.<String, Serializable>put(
                    "employeeId", employeeId
                ).put(
                    "createdBy", userId
                ).build();

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setAttributes(attributes);
        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setCompanyId(CompanyThreadLocal.getCompanyId());
        serviceContext.setUserId(userId);
        serviceContext.setScopeGroupId(groupId);

        return serviceContext;
    }

    private String _createEmailAddressDomain(
            String firstName, String lastName, String domain) {

        return firstName.toLowerCase() + "." + lastName.toLowerCase() + "@" + domain;
    }

    private void _createManager(
            long companyId, long employeeId, long groupId, long mvccVersion)
        throws NoSuchEmployeeException {

        _managerLocalService.createManager(
                groupId, companyId, employeeId, mvccVersion);
    }

    @Deprecated
    private List<Employee> _findEmployeesByDynamicQuery(String key) {
        DynamicQuery dynamicQuery = dynamicQuery();

        Disjunction disjunction = RestrictionsFactoryUtil.disjunction();

        disjunction.add(
            RestrictionsFactoryUtil.eq(
                    "employeeId", Long.parseLong(key)));
        disjunction.add(
            RestrictionsFactoryUtil.ilike(
                    "department", key));
        disjunction.add(
            RestrictionsFactoryUtil.ilike(
                    "firstName", key));

        dynamicQuery.add(disjunction);

        return dynamicQuery(
                dynamicQuery, QueryUtil.ALL_POS, QueryUtil.ALL_POS);
    }

    private List<Employee> _getEmployees(Hits hits) throws PortalException {
        List<Employee> employees = new ArrayList<>();

        for (Document document : hits.toList()) {
            long employeeId = GetterUtil.getLong(
                    document.get(Field.ENTRY_CLASS_PK));

            try {
                employees.add(
                        employeePersistence.findByPrimaryKey(employeeId));
            }
            catch (NoSuchEmployeeException noSuchEmployeeException) {
                if (_log.isDebugEnabled()) {
                    _log.warn(
                            "Employee index not found " + employeeId,
                            noSuchEmployeeException);
                }

                Indexer<Employee> indexer = IndexerRegistryUtil.getIndexer(
                        Employee.class);

                long companyId = GetterUtil.getLong(
                        document.get(Field.COMPANY_ID));

                indexer.delete(companyId, document.getUID());
            }
        }

        return employees;
    }

    private void _validate(
            String firstName, String lastName, String position,
            int level, String department)
        throws NoSuchManagerException {

        try {
            _validNamesPattern(firstName, lastName, position, department);

            if (employeePersistence.fetchByF_L_First(
                    firstName, lastName, null) != null) {

                throw new RuntimeException(
                    "Employee with the first " + firstName + " and last " +
                            lastName + " name already exists");
            }
            else {
                Set<String> departmentsList = new HashSet<>();

                Collections.addAll(
                    departmentsList,
                    EmployeeStructureConstants.DEPARTMENTS);

                if (!departmentsList.contains(department)) {
                    throw new NoSuchManagerException(
                            "No such department with the name " + department);
                }

                _validateLevelAndPosition(level, position);
            }
        }
        catch (RuntimeException runtimeException) {
            throw new NoSuchManagerException(runtimeException);
        }

    }

    private void _validateLevelAndPosition(int level, String position)
        throws RuntimeException {

        Map<String, int[]> positionsAndLevels =
                EmployeeStructureConstants.getAvailablePositionsMap();

        if (positionsAndLevels.containsKey(position)) {
            List<String> levelsList = new ArrayList<>();

            for (int l : positionsAndLevels.get(position)) {
                levelsList.add(String.valueOf(l));
            }

            if (!levelsList.contains(String.valueOf(level))) {
                throw new RuntimeException(
                    "No such level available for this position " + position +
                        "level invaluable " + level);
            }
        }
        else {
            throw new RuntimeException(
                "Not exist available position with the name " + position);
        }
    }

    private void _validatePromotion(
            boolean betweenLevels, String department, String firstName,
            String lastName, int newLevel, int oldLevel, String newPosition,
            String oldPosition)
        throws NoSuchManagerException {

        try {
            if (employeePersistence.fetchByF_L_First(
                    firstName, lastName, null) == null) {
                throw new NoSuchManagerException(
                        StringBundler.concat(
                                "Employee not found with the name ",
                                firstName, StringPool.SPACE, lastName));
            }

            if (!betweenLevels && newPosition.equals(oldPosition)) {
                throw new RuntimeException(
                        "Invalid promotion transaction. " +
                                "Position cannot be identical to actual.");
            }
            else if (betweenLevels && !newPosition.equals(oldPosition)) {
                if (newLevel <= oldLevel) {
                    throw new RuntimeException(
                            "Invalid promotion transaction between levels.");
                }
            }
            else {
                _validateLevelAndPosition(newLevel, newPosition);

                Map<String, Long> hierarchyEngineersPositionsHashMap =
                        EmployeeStructureConstants.getHierarchyPositionEngineerMap();

                if (hierarchyEngineersPositionsHashMap.containsKey(newPosition)) {
                    long hierarchyNewPosition =
                            hierarchyEngineersPositionsHashMap.get(newPosition);
                    long hierarchyOldPosition =
                            hierarchyEngineersPositionsHashMap.get(oldPosition);

                    if (hierarchyOldPosition >= hierarchyNewPosition) {
                        throw new RuntimeException(
                                "Invalid promotion transaction. Actual level cannot be" +
                                        " equal or larger than new level.");
                    }
                }
                else {
                    throw new RuntimeException(
                            "New position is not available for department " +
                                    department);
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException);
        }
    }

    private void _validNamesPattern(String ...contents) {
        for (String content : contents) {
            Pattern pattern = Pattern.compile("[A-z]");

            Matcher matcher = pattern.matcher(content);

            if (!matcher.find()) {
                throw new RuntimeException(
                    StringBundler.concat(
                        "Invalid field name ",
                        content, "\n Is not applying the pattern ",
                        pattern.pattern()));
            }
        }
    }

    private static final Log _log = LogFactoryUtil.getLog(
            EmployeeLocalServiceImpl.class);

    @Reference
    private ClassNameLocalService _classNameLocalService;

    @Reference
    private ManagerLocalService _managerLocalService;

    @Reference
    private UserLocalService _userLocalService;

}