package management.web.portlet.action;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletException;

import management.web.constants.ManagementPortletKeys;

import org.osgi.service.component.annotations.Component;

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
        throws PortletException {

    }
}
