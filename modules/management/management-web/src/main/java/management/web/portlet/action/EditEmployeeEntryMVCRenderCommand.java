package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import management.web.constants.ManagementPortletKeys;

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

        return "/management/edit_employee_entry.jsp";
    }

}
