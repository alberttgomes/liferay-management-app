package management.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.helper.EmployeeManagementHelper;

import java.io.IOException;
import java.util.Objects;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.servlet.http.HttpServletRequest;

import management.web.constants.EmployeeRequestConstant;
import management.web.constants.ManagementPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = {
        "javax.portlet.name=" + ManagementPortletKeys.EMPLOYEE_DASHBOARD_WEB,
        "mvc.command.name=/management/open_request_employee"
    },
    service = MVCActionCommand.class
)
public class OpenRequestEmployeeMVCActionCommand extends BaseMVCActionCommand {

    @Override
    public void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws PortalException, IOException {

        String description = ParamUtil.getString(actionRequest, "description");
        long employeeId = ParamUtil.getInteger(actionRequest, "employeeId");
        int newLevel = ParamUtil.getInteger(actionRequest, "newLevel");
        String newPosition = ParamUtil.getString(actionRequest, "newPosition");
        String reason = ParamUtil.getString(actionRequest, "reason");
        long userManagerId = ParamUtil.getLong(actionRequest, "userManagerId");
        long type = Long.parseLong(ParamUtil.getString(actionRequest, "type"));
        boolean betweenLevels = ParamUtil.getBoolean(actionRequest, "betweenLevels");

        if (type == EmployeeRequestConstant.PROMOTION) {
            Employee employee = _employeeLocalService.getEmployee(employeeId);

            if (Objects.isNull(employee)) {
                return;
            }

            HttpServletRequest httpServletRequest =
                    _portal.getHttpServletRequest(actionRequest);

            ThemeDisplay themeDisplay =
                    (ThemeDisplay)httpServletRequest.getAttribute(
                            WebKeys.THEME_DISPLAY);

            employee = _employeeLocalService.employeePromoting(
                    newPosition, themeDisplay.getUserId(), employee.getDepartment(),
                    newLevel, employeeId, true, betweenLevels);

            actionRequest.setAttribute(
                    "employeeId", employee.getEmployeeId());

            sendRedirect(actionRequest, actionResponse);
        }
        else if (type == EmployeeRequestConstant.DISMISSAL_EMPLOYEE) {
            _employeeManagementHelper.dismissalEmployee(employeeId, userManagerId);

            actionResponse.sendRedirect(
                    PortletURLBuilder.createRenderURL(
                            _portal.getLiferayPortletResponse(actionResponse)
                    ).setMVCPath(
                            "/view.jsp"
                    ).buildString());
        }
        else {
            if (_log.isErrorEnabled()) {
                _log.error("Unknown action type: " + type);
            }
        }

    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private EmployeeManagementHelper _employeeManagementHelper;

    @Reference
    private Portal _portal;

    private static final Log _log = LogFactoryUtil.getLog(
            OpenRequestEmployeeMVCActionCommand.class);

}
