package management.web.portlet.action;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
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

import management.web.display.EmployeeDisplay;
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

        try {
            String cmd = ParamUtil.getString(actionRequest, Constants.CMD);

            String redirectURL = ParamUtil.getString(
                    actionRequest, "redirect");

            if (!cmd.equals(Constants.ADD)) {
                if (cmd.equals(Constants.EDIT) || cmd.equals(Constants.UPDATE)) {
                    Employee employee = _updateEmployee(actionRequest);

                    if (employee != null) {
                        EmployeeDisplay employeeDisplay = EmployeeDisplay.of(employee);

                        actionRequest.setAttribute(
                                ManagementPortletKeys.EMPLOYEE_DISPLAY,
                                employeeDisplay);

                        sendRedirect(actionRequest, actionResponse, redirectURL);
                    }
                }
                else if (cmd.equals(Constants.DELETE)) {
                    long employeeId = ParamUtil.getLong(
                            actionRequest, "employeeId");

                    _employeeLocalService.deleteEmployee(employeeId);
                }
                else {
                    throw new RuntimeException("Unknown command: " + cmd);
                }
            }
            else {
                Employee employee = _addEmployee(actionRequest);

                if (employee != null) {
                    EmployeeDisplay employeeDisplay = EmployeeDisplay.of(
                            employee);

                    actionRequest.setAttribute(
                            ManagementPortletKeys.EMPLOYEE_DISPLAY,
                            employeeDisplay);

                    sendRedirect(actionRequest, actionResponse, redirectURL);
                }
                else {
                    throw new RuntimeException(
                            "An error occurred during " +
                                    EditEmployeeEntryMVCActionCommand.class +
                                    " mvc action.");
                }
            }
        }
        catch (RuntimeException runtimeException) {
            throw new RuntimeException(runtimeException.getCause());
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
                managerIdPK, isManager, user);
    }

    private Employee _updateEmployee(ActionRequest actionRequest)
        throws PortalException {

        long employeeId = ParamUtil.getLong(
                actionRequest, "employeeId");

        Employee employee =
                _employeeLocalService.fetchEmployee(employeeId);

        String department = ParamUtil.getString(
                actionRequest, "department");
        String firstName = ParamUtil.getString(
                actionRequest, "firstName");
        String lastName = ParamUtil.getString(
                actionRequest, "lastName");
        boolean isManager = ParamUtil.getBoolean(
                actionRequest, "isManager");
        int level = ParamUtil.getInteger(
                actionRequest, "level");
        long managerIdPK = ParamUtil.getLong(
                actionRequest, "managerIdPK");
        String position = ParamUtil.getString(
                actionRequest, "position");

        employee.setDepartment(
                department == null || department.isEmpty() ?
                        employee.getDepartment() : department);
        employee.setFirstName(
                firstName == null || firstName.isEmpty() ?
                        employee.getFirstName() : firstName);
        employee.setLastName(
                lastName == null || lastName.isEmpty() ?
                        employee.getLastName() : lastName);
        employee.setIsManager(
                isManager || employee.getIsManager());
        employee.setLevel(
                level > 0 ? level : employee.getLevel());
        employee.setManagerIdFK(
                managerIdPK > 0 ? managerIdPK : employee.getManagerIdFK());
        employee.setPosition(
                position == null || position.isEmpty() ?
                        employee.getPosition() : position);

        return _employeeLocalService.updateEmployee(employee);
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

}
