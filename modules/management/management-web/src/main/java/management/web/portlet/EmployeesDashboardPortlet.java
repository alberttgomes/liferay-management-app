package management.web.portlet;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import java.io.IOException;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;

import management.web.constants.ManagementPortletKeys;
import management.web.display.context.ManagementDisplayContext;

import management.web.display.context.ViewEmployeeStaffMembersDisplayContext;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
    property = {
        "com.liferay.portlet.display-category=Management",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Employees Dashboard Portlet",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/management/employee_dashboard.jsp",
        "javax.portlet.name=" + ManagementPortletKeys.EMPLOYEE_DASHBOARD_WEB,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class EmployeesDashboardPortlet extends MVCPortlet {

    @Override
    public void render(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        HttpServletRequest httpServletRequest =
                PortalUtil.getHttpServletRequest(renderRequest);

        ManagementDisplayContext managementDisplayContext =
                new ManagementDisplayContext(
                        httpServletRequest, _userLocalService);

        renderRequest.setAttribute(
                WebKeys.PORTLET_DISPLAY_CONTEXT, managementDisplayContext);

        super.render(renderRequest, renderResponse);
    }

    @Reference
    private UserLocalService _userLocalService;

}
