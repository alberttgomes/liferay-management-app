package management.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
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

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import java.util.Locale;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
        property = {
            "javax.portlet.name=" + ManagementPortletKeys.MANAGEMENT_WEB,
            "mvc.command.name=/management/edit_employee_management"
        },
        service = MVCActionCommand.class
)
public class EditEmployeeEntryMVCActionCommand extends BaseMVCActionCommand {

    @Override
    protected void doProcessAction(
            ActionRequest actionRequest, ActionResponse actionResponse)
        throws Exception {

        String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

        try {
            if (cmd.equals(Constants.ADD)) {
                Employee employee = _addEmployee(actionRequest);

                if (employee != null) {
                    _log.debug("Employee entry was completed with success " +
                            employee);
                }
                else {
                    _log.error("An error occurred during " +
                            EditEmployeeEntryMVCActionCommand.class + " mvc action.");
                }
            }
            else if (cmd.equals(Constants.DELETE)) {
                long employeeId = ParamUtil.getLong(
                        actionRequest, "employeeId");

                _employeeLocalService.deleteEmployee(employeeId);
            }
            else {
                throw new Exception("Unknown command: " + cmd);
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
        boolean isManager = ParamUtil.getBoolean(actionRequest, "isManager");
        String lastName = ParamUtil.getString(actionRequest, "lastName");
        int level = ParamUtil.getInteger(actionRequest, "level");
        long managerIdPK = ParamUtil.getInteger(actionRequest, "managerIdPK");
        String position = ParamUtil.getString(actionRequest, "position");

        Locale locale = actionRequest.getLocale();

        User user = PortalUtil.getUser(actionRequest);

        return _employeeLocalService.addEmployee(
                firstName, lastName, department, position,
                level, locale.getCountry(), WorkflowConstants.STATUS_APPROVED,
                managerIdPK, isManager, user, CompanyThreadLocal.getCompanyId());
    }

    private static final Log _log = LogFactoryUtil.getLog(
            EditEmployeeEntryMVCActionCommand.class);

    @Reference
    private EmployeeLocalService _employeeLocalService;

}
