package management.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import java.io.IOException;
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
        long type = Long.parseLong(ParamUtil.getString(actionRequest, "type"));

        if (type == EmployeeRequestConstant.PROMOTION) {
            Employee employee = _employeeLocalService.getEmployee(employeeId);

            if (employee == null) {
                return;
            }

            HttpServletRequest httpServletRequest =
                    _portal.getHttpServletRequest(actionRequest);

            ThemeDisplay themeDisplay =
                    (ThemeDisplay)httpServletRequest.getAttribute(
                            WebKeys.THEME_DISPLAY);

            employee = _employeeLocalService.employeePromoting(
                    newPosition, themeDisplay.getUserId(), employee.getDepartment(),
                    newLevel, employeeId, true);

            actionRequest.setAttribute(
                    "employeeId", employee.getEmployeeId());

            sendRedirect(actionRequest, actionResponse);
        }
        else if (type == EmployeeRequestConstant.DISMISSAL_EMPLOYEE) {
            _employeeLocalService.deleteEmployee(employeeId);

            actionResponse.sendRedirect(
                    PortletURLBuilder.createRenderURL(
                            _portal.getLiferayPortletResponse(actionResponse)
                    ).setMVCPath(
                            "/view.jsp"
                    ).buildString());
        }

    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private Portal _portal;

}
