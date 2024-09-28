package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

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
                "mvc.command.name=/management/view_request_employee"
        },
        service = MVCRenderCommand.class
)
public class OpenRequestEmployeeMVCRenderCommand implements MVCRenderCommand {

    @Override
    public String render(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws PortletException {

        HttpServletRequest httpServletRequest =
                PortalUtil.getHttpServletRequest(renderRequest);

        ManagementDisplayContext managementDisplayContext =
                new ManagementDisplayContext(
                        httpServletRequest, _userLocalService);

        renderRequest.setAttribute(
                ManagementPortletKeys.EMPLOYEE_DISPLAY,
                managementDisplayContext);

        return "/management/view_request_employee.jsp";
    }

    @Reference
    private UserLocalService _userLocalService;

}
