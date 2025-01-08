package com.management.app.internal.servlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.model.UserConstants;
import com.liferay.portal.kernel.servlet.BaseFilter;
import com.liferay.portal.kernel.util.PortalUtil;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = {
        "servlet-context-name=",
        "servlet-filter-name=Verify users permission access",
        "url-pattern=/web/portal/"
    },
    service = Filter.class
)
public class ManagementTypeUserRedirectFilter extends BaseFilter {

    @Override
    protected Log getLog() {
        return _log;
    }

    @Override
    protected void processFilter(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse, FilterChain filterChain)
        throws Exception {

        User user = PortalUtil.getUser(httpServletRequest);

        if (user == null || user.getType() == UserConstants.TYPE_GUEST) {

            httpServletResponse.sendRedirect("/web/portal/guests");

            return;
        }

        Employee employee = _employeeLocalService.fetchEmployeeByUserId(
                    user.getCompanyId(), user.getUserId());

        if (employee == null) {

            httpServletResponse.sendRedirect("/web/portal/guests");

            return;
        }
        else if (!employee.getDepartment().isEmpty()) {

            httpServletResponse.sendRedirect("/web/portal/employees");

            return;
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    private static final Log _log = LogFactoryUtil.getLog(
            ManagementTypeUserRedirectFilter.class);

}
