package com.management.app.internal.helper;

import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalService;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.helper.EmployeeManagementHelper;

import java.util.Map;
import java.util.Objects;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(service = EmployeeManagementHelper.class)
public class EmployeeManagementHelperImpl implements EmployeeManagementHelper {

    @Override
    public Object addDependentHealthPlan(
            long employeeId, long userManagerId) throws RuntimeException {

        return null;
    }

    @Override
    public void dismissalEmployee(
            long employeeId, long userManagerId) throws PortalException {

        Employee employee = _employeeLocalService.fetchEmployee(employeeId);

        if (Objects.isNull(employee)) {
            throw new RuntimeException(
                    "No employee found for employee id " + employeeId);
        }

        User userManager = _userLocalService.fetchUser(userManagerId);

        if (Objects.nonNull(userManager)) {
            Employee managerEmployee = _employeeLocalService.fetchEmployeeByUserId(
                    userManager.getCompanyId(), userManager.getUserId());

            if (managerEmployee.getUserId() == userManagerId) {
                _employeeLocalService.deleteEmployee(employeeId);
            }
            else {
                if (_log.isErrorEnabled()) {
                    _log.error(StringBundler.concat(
                            "Operation not allowed for user id ",
                            userManagerId, " and employee id ", employeeId));
                }
            }
        }
    }

    @Override
    public Map<String, Object> requestBenefitEducation(
            String description, long employeeId, String value, String type)
        throws RuntimeException {

        return null;
    }

    @Override
    public void requestDismissalSelf(
            String description, long employeeId, String value, String type)
        throws RuntimeException {

    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private UserLocalService _userLocalService;

    private static final Log _log = LogFactoryUtil.getLog(
            EmployeeManagementHelperImpl.class);

}
