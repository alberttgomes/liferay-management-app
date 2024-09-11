package management.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.CompanyThreadLocal;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.workflow.WorkflowConstants;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalService;

import management.web.constants.ManagementPortletKeys;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import java.util.Locale;

/**
 * @author Albert Cabral
 */
@Component(
        property = {
            "javax.portlet.name=" + ManagementPortletKeys.MANAGEMENT_WEB,
            "mvc.command.name=/management/add_employee_entry"
        },
        service = MVCActionCommand.class
)
public class AddEmployeeEntryMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        try {
            if (cmd.equals(Constants.ADD)) {
                if (_addEmployee(actionRequest) == null) {
                    throw new Exception(
                            "Unable to create employee");
                }
            }
        }
        catch (Exception exception) {
            throw new Exception(exception.getCause());
        }

    }

    private Employee _addEmployee(
            ActionRequest actionRequest) throws PortalException {

        String department = ParamUtil.getString(actionRequest, "department");
        String firstName = ParamUtil.getString(actionRequest, "firstName");
        String lastName = ParamUtil.getString(actionRequest, "lastName");
        boolean isManager = ParamUtil.getBoolean(actionRequest, "isManager");
        String position = ParamUtil.getString(actionRequest, "position");
        int level = ParamUtil.getInteger(actionRequest, "level");
        long managerIdPK = ParamUtil.getInteger(actionRequest, "managerIdPK");

        Locale locale = actionRequest.getLocale();

        User user = PortalUtil.getUser(actionRequest);

        return _employeeLocalService.addEmployee(
                firstName, lastName, department, position,
                level, locale.getCountry(), WorkflowConstants.STATUS_APPROVED,
                managerIdPK, isManager, user, CompanyThreadLocal.getCompanyId());
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

}
