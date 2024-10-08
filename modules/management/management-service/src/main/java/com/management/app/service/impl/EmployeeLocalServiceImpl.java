/**
 * SPDX-FileCopyrightText: (c) 2024 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.management.app.service.impl;

import com.liferay.counter.kernel.service.CounterLocalServiceUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.petra.string.StringPool;
import com.liferay.portal.aop.AopService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.search.*;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.service.*;
import com.liferay.portal.kernel.util.*;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import com.management.app.constants.ManagementConstants;
import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.exception.NoSuchManagerException;
import com.management.app.internal.helper.EmployeeStructureHelper;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.ManagerLocalService;
import com.management.app.service.base.EmployeeLocalServiceBaseImpl;

import java.io.Serializable;
import java.util.*;
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

    @Override
    public Employee addEmployee(
            String firstName, String lastName, String department, String position,
            int level, String stateCode, int status, long managerIdPK,
            boolean isManager, User user)
        throws PortalException {

        long employeeId = CounterLocalServiceUtil.increment();

        _validate(firstName, lastName, position, level, department, false);

        return _addEmployee(
                firstName, lastName, department, position,
                level, stateCode, employeeId, isManager, user);
    }

    @Override
    public Employee deleteEmployee(long employeeId) throws NoSuchEmployeeException {
        if (employeePersistence.findByEmployeeId(employeeId).isEmpty() ||
                employeePersistence.findByEmployeeId(employeeId) == null) {

            throw new NoSuchEmployeeException(
                    "No such employee with id: " + employeeId);
        }
        else {
            _log.debug("Deleting employee with id: " + employeeId);

            return employeePersistence.remove(employeeId);
        }
    }

    @Override
    public Employee getEmployee(long employeeId) {
        return employeePersistence.fetchByPrimaryKey(employeeId);
    }

    @Override
    public List<Employee> getAllEmployeeByManager(
            long managerIdPK, long companyId, boolean hasPermission)
        throws NoSuchEmployeeException, NoSuchManagerException {

        try {
            Manager manager = _managerLocalService.getManager(managerIdPK);

            if (manager == null) {
                throw new NoSuchManagerException(
                        "No manager found for id: " + managerIdPK);
            }

            List<Employee> employees = employeePersistence.findAll();

            List<Employee> associatedEmployees = new ArrayList<>();

            if (employees.isEmpty()) {
                throw new NoSuchEmployeeException(
                        "Associated employees list is empty");
            }

            _log.debug("All employees there are managers \n");

            for (Employee employee : employees) {
                if (employee.getManagerIdFK() == managerIdPK) {
                    _log.debug(StringBundler.concat(
                            "Employee: ", employee.getFirstName(),
                            StringPool.SPACE, employee.getLastName()));

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
                // default RuntimeException

                throw new RuntimeException(runtimeException);
            }
        }

    }

    @Override
    public Employee employeePromoting(
            String position, long userId, String department, int level,
            long employeeId, boolean isManager)
        throws PortalException {

        try {
            User user = userLocalService.fetchUser(userId);

            if (user == null || !user.isActive()) {
                throw new RuntimeException(
                        "Operation not allowed by the user id " + userId);
            }

            _log.debug("Are promoting by " + user.getFullName());

            Employee employee = getEmployee(employeeId);

            if (employee == null) {
                _log.error("No exists employee with id " + employeeId);

                return null;
            }

            _validate(
                    employee.getFirstName(), employee.getLastName(),
                    position, level, department, true);

            employee.setDepartment(department);
            employee.setLevel(level);
            employee.setPosition(position);
            employee.setModifiedDate(new Date());

            employee = employeePersistence.update(employee);

            if (isManager) {
                _createManager(
                        employee.getCompanyId(),
                        employee.getEmployeeId(),
                        employee.getGroupId(),
                        employee.getMvccVersion());
            }

            User employeeUser = _userLocalService.fetchUser(
                    employee.getUserId());

            if (employeeUser != null) {
                employeeUser.setJobTitle(employee.getPosition());

                _userLocalService.updateUser(employeeUser);
            }
            else {
                if (_log.isWarnEnabled()) {
                    _log.warn(StringBundler.concat(
                            "Cannot to update user job title ",
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
            long companyId, String className, String keywords,
            LinkedHashMap<String, Object> params, int start, int end, Sort sort)
        throws PortalException {

        SearchContext searchContext = _buildSearchContext(
                companyId, className, keywords, params, start, end, sort);

        return _searchEmployees(searchContext);
    }

    private Employee _addEmployee(
            String firstName, String lastName, String department, String position,
            int level, String stateCode, long employeeId, boolean isManager,
            User user)
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
        employee.setGroupId(user.getGroupId());
        employee.setMvccVersion(0);

        if (isManager) {
            _createManager(
                    employee.getCompanyId(),
                    employee.getEmployeeId(),
                    employee.getGroupId(),
                    employee.getMvccVersion());
        }

        String emailAddress = _createEmailAddressDomain(
                employee.getFirstName(), employee.getLastName(),
                ManagementConstants.MANAGEMENT_DOMAIN);

        String screenNameUser =
                StringBundler.concat(
                        employee.getFirstName(),
                        StringPool.SPACE, employee.getLastName());

        int birthdayMonth = Calendar.SEPTEMBER;
        int birthdayDay = 19;
        int birthdayYear = 1995;

        ServiceContext serviceContext = _buildServiceContext(
                user.getUserId(), employee.getEmployeeId(),
                employee.getGroupId());

        User employeeUser = _userLocalService.addUser(
                user.getUserId(), employee.getCompanyId(), false, "batman",
                "batman", true, screenNameUser, emailAddress,
                user.getLocale(), employee.getFirstName(), null, employee.getLastName(),
                0L, 0L, true, birthdayMonth,
                birthdayDay, birthdayYear, employee.getPosition(), UserConstants.TYPE_REGULAR,
                null, null, null, null,
                true, serviceContext);

        employeeUser.setUuid(employee.getUuid());

        employeeUser = _userLocalService.updateUser(employeeUser);

        employee.setUserId(employeeUser.getUserId());

        Employee managerEmployee = fetchEmployeeByUserId(
                employee.getCompanyId(), user.getUserId());

        employee.setManagerIdFK(
                managerEmployee == null ? user.getUserId() :
                        managerEmployee.getManagerIdFK());

        employee = employeePersistence.update(employee);

        _log.info("Create employee " + employee.getFirstName());

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
                        "keywords", keywords
                ).put(
                        "params",
                        LinkedHashMapBuilder.<String, Object>put(
                                "keywords", keywords
                        ).build()
                ).put(
                        "jobTitle", keywords
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

    private ServiceContext _buildServiceContext(
            long employeeId, long groupId, long userId) {

        Map<String, Serializable> attributes =
                HashMapBuilder.<String, Serializable>put(
                            "employeeId", employeeId)
                        .put(
                            "createdBy", userId)
                        .build();

        ServiceContext serviceContext = new ServiceContext();

        serviceContext.setAddGroupPermissions(true);
        serviceContext.setAddGuestPermissions(true);
        serviceContext.setCompanyId(CompanyThreadLocal.getCompanyId());
        serviceContext.setUserId(userId);
        serviceContext.setScopeGroupId(groupId);
        serviceContext.setAttributes(attributes);

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

    private List<Employee> _getEmployees(Hits hits) throws PortalException {
        List<Document> documents = hits.toList();

        List<Employee> employees = new ArrayList<>(documents.size());

        for (Document document : documents) {
            long employeeId = GetterUtil.getLong(
                    document.get(Field.ENTRY_CLASS_PK));

            Employee employee = fetchEmployee(employeeId);

            if (employee == null) {
                employees = null;

                Indexer<Employee> indexer = IndexerRegistryUtil.getIndexer(
                        Employee.class);

                long companyId = GetterUtil.getLong(
                        document.get(Field.COMPANY_ID));

                indexer.delete(companyId, document.getUID());
            }
            else if (employees != null) {
                employees.add(employee);
            }
        }

        return employees;
    }

    private BaseModelSearchResult<Employee> _searchEmployees(
            SearchContext searchContext)
        throws PortalException {

        Indexer<Employee> indexer = IndexerRegistryUtil.nullSafeGetIndexer(
                Employee.class);

        for (int i = 0; i < 10; i++) {
            Hits hits = indexer.search(searchContext);

            List<Employee> employees = _getEmployees(hits);

            if (employees != null) {
                return new BaseModelSearchResult<>(employees, hits.getLength());
            }
        }

        throw new SearchException(
                "Unable to find any employees");
    }

    private void _validate(
            String firstName, String lastName, String position,
            int level, String department, boolean isPromotion)
        throws NoSuchManagerException {

        try {
            if (!_validRegex(firstName) && !_validRegex(lastName)) {
                throw new RuntimeException(
                        "No valid fields names " + firstName + ", and " +
                                lastName);
            }
            else if (!_validRegex(position) && _validRegex(department)) {
                throw new RuntimeException(
                        "No valid field names " + position + ", and " +
                                department);
            }
            else {
                Map<String, String> departmentMap = HashMapBuilder.put(
                        "department", department).build();

                JSONObject jsonObject = _departmentsJSONObject();

                jsonObject = (JSONObject)
                        jsonObject.get(departmentMap.get("department"));

                if (jsonObject.get(position) == null) {
                    throw new RuntimeException(
                            "This position not is available " + position);
                }

                JSONObject positionObject = (JSONObject) jsonObject.get(position);

                Integer[] levels = (Integer[]) positionObject.get("level");

                boolean available = false;

                for (int l : levels) {
                    if (l == level) {
                        _log.debug("The level " + level + " to job position " +
                                position + " it's available on");

                        available = true;

                        break;
                    }
                }

                if (!available) {
                    throw new RuntimeException("No available level " + level);
                }

                if (isPromotion) {
                    if (employeePersistence.fetchByF_L_First(
                            firstName, lastName, null) == null) {

                        throw new NoSuchManagerException(
                                StringBundler.concat(
                                        "Employee not found with the name ",
                                        firstName, StringPool.SPACE, lastName));
                    }
                }
                else {
                    if (employeePersistence.fetchByF_L_First(
                            firstName, lastName, null) != null) {

                        throw new NoSuchManagerException(
                                "Employee with the first " + firstName + " and last " +
                                        lastName + " name already exists");
                    }
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw new NoSuchManagerException(runtimeException);
        }

    }

    private void _validatePromotion(
            boolean betweenLevels, String department, int newLevel, int oldLevel,
            String newPosition, String oldPosition) {

        if (department.equals(
                EmployeeStructureHelper.DEPARTMENT_ENGINEER)) {

            if (newPosition.equals(oldPosition)) {
                return;
            }

            Map<String, Long> hierarchyEngineersPositionsHashMap =
                    EmployeeStructureHelper.HIERARCHY_ENGINEERS_POSITIONS_HASH_MAP;

            if (hierarchyEngineersPositionsHashMap.containsKey(newPosition)) {
                long hierarchyOldPosition =
                        hierarchyEngineersPositionsHashMap.get(
                                oldPosition);

                long hierarchyNewPosition =
                        hierarchyEngineersPositionsHashMap.get(
                                newPosition);

                if (hierarchyOldPosition >= hierarchyNewPosition) {
                    throw new RuntimeException(
                            "Invalid promotion transaction. Old position is" +
                                    " and larger than new position");
                }
            }
        }
        else if (department.equals(
                EmployeeStructureHelper.DEPARTMENT_GENERAL)) {

            if (newPosition.equals(oldPosition)) {
                return;
            }
        }

    }

    private JSONObject _departmentsJSONObject() {
        return JSONUtil.put(
                "engineer",
                JSONUtil.put(
                        "Assoc Software Engineer",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3, 4})
                        )
                ).put(
                        "Software Engineer",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5})
                        )
                ).put(
                        "Senior Software Engineer",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5, 6})
                        )
                ).put(
                        "Team Leader Software Engineer",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3})
                        )
                )
        ).put(
                "general",
                JSONUtil.put(
                        "Marketing",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3})
                        )
                ).put(
                        "Sales Product",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3})
                        )
                ).put(
                        "Operations",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3, 4})
                        )
                ).put(
                        "Product Design",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3})
                        )
                ).put(
                        "Human Resources",
                        JSONUtil.put(
                                "level",
                                ArrayUtil.append(new Integer[]{1, 2, 3, 4, 5})
                        )
                )
        );
    }

    private boolean _validRegex(String content) {
        Pattern pattern = Pattern.compile("[A-z]");

        Matcher matcher = pattern.matcher(content);

        return matcher.find();
    }

    private static final Log _log = LogFactoryUtil.getLog(
            EmployeeLocalServiceImpl.class);

    @Reference
    private ManagerLocalService _managerLocalService;

    @Reference
    private UserLocalService _userLocalService;

}