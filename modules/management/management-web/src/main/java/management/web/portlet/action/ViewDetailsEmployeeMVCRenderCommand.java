package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import management.web.constants.ManagementPortletKeys;
import management.web.display.EmployeeDisplay;

import org.osgi.service.component.annotations.Component;

/**
 * @author Albert Cabral
 */
@Component(
        property = {
                "javax.portlet.name=" + ManagementPortletKeys.EMPLOYEE_DASHBOARD_WEB,
                "mvc.command.name=/management/view_details_employee"
        },
        service = MVCRenderCommand.class
)
public class ViewDetailsEmployeeMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws PortletException {

        EmployeeDisplay employeeDisplay = (EmployeeDisplay)
                renderRequest.getAttribute(
                        ManagementPortletKeys.EMPLOYEE_DISPLAY);

        renderRequest.setAttribute(
                ManagementPortletKeys.EMPLOYEE_DISPLAY,
                employeeDisplay);

        return "/management/view_details_employee.jsp";

    }

}
