package management.web.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import javax.portlet.Portlet;
import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import management.web.constants.ManagementPortletKeys;
import management.web.model.EmployeeDetails;

import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Component;

/**
 * @author Albert Cabral
 */
@Component(
    property = {
        "com.liferay.portlet.display-category=Management",
        "com.liferay.portlet.header-portlet-css=/css/main.css",
        "com.liferay.portlet.instanceable=true",
        "javax.portlet.display-name=Employees Details",
        "javax.portlet.init-param.template-path=/",
        "javax.portlet.init-param.view-template=/management/pages/details.jsp",
        "javax.portlet.name=" + ManagementPortletKeys.EMPLOYEE_DETAILS_WEB,
        "javax.portlet.resource-bundle=content.Language",
        "javax.portlet.security-role-ref=power-user,user"
    },
    service = Portlet.class
)
public class EmployeeDetailsPortlet extends MVCPortlet {

    @Override
    public void render(
            RenderRequest renderRequest, RenderResponse renderResponse)
        throws IOException, PortletException {

        HttpServletRequest httpServletRequest =
                PortalUtil.getHttpServletRequest(renderRequest);

        ThemeDisplay themeDisplay =
            (ThemeDisplay)
                httpServletRequest.getAttribute(
                    WebKeys.THEME_DISPLAY);

        try {
            User employeeUser = _userLocalService.getUser(themeDisplay.getUserId());

            Employee employee = _employeeLocalService.fetchEmployeeByUserId(
                    employeeUser.getCompanyId(), employeeUser.getUserId());

            renderRequest.setAttribute(
                "EmployeeDetails",
                new EmployeeDetails(
                    employee.getDepartment(), employeeUser.getEmailAddress(),
                    employee.getEmployeeId(), employeeUser.getFullName(),
                    employee.getLevel(), employee.getPosition(),
                    employeeUser.getCreateDate()));
        }
        catch (PortalException portalException) {
            throw new RuntimeException(portalException);
        }

        super.render(renderRequest, renderResponse);
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private UserLocalService _userLocalService;

}
