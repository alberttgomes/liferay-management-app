package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.Portal;
import com.liferay.portal.kernel.util.WebKeys;
import management.web.constants.ManagementPortletKeys;

import management.web.display.context.ManagementDisplayContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

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

        ThemeDisplay themeDisplay =
                (ThemeDisplay)renderRequest.getAttribute(
                        WebKeys.THEME_DISPLAY);

        if (!themeDisplay.isSignedIn()) {
            SessionErrors.add(renderRequest, PrincipalException.class);

            return "/error.jsp";
        }

        long employeeId = ParamUtil.getLong(
                renderRequest, "employeeId");

        renderRequest.setAttribute("employeeId", employeeId);

        HttpServletRequest httpServletRequest =
                _portal.getHttpServletRequest(renderRequest);

        ManagementDisplayContext managementDisplayContext =
                new ManagementDisplayContext(
                        httpServletRequest, _userLocalService);

        renderRequest.setAttribute(
                ManagementPortletKeys.EMPLOYEE_DISPLAY,
                managementDisplayContext);

        return "/management/view_details_employee.jsp";
    }

    @Reference
    private Portal _portal;

    @Reference
    private UserLocalService _userLocalService;

}
