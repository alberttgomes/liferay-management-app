package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import management.web.constants.ManagementPortletKeys;
import management.web.display.EmployeeDisplay;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

/**
 * @author Albert Cabral
 */
@Component(
        property = {
                "javax.portlet.name=" + ManagementPortletKeys.MANAGEMENT_WEB,
                "mvc.command.name=/management/edit_employee_management"
        },
        service = MVCRenderCommand.class
)
public class EditEmployeeEntryMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws PortletException {

        ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(
                WebKeys.THEME_DISPLAY);

        if (!themeDisplay.isSignedIn()) {
            SessionErrors.add(renderRequest, PrincipalException.class);

            return "/error.jsp";
        }

        renderRequest.setAttribute(
                "description",
                "Welcome to the Employee Management Portal");

        EmployeeDisplay employeeDisplay = (EmployeeDisplay)
                renderRequest.getAttribute(
                        ManagementPortletKeys.EMPLOYEE_DISPLAY);

        renderRequest.setAttribute(
                ManagementPortletKeys.EMPLOYEE_DISPLAY,
                employeeDisplay);

        return "/management/employee_entry.jsp";

    }

}
