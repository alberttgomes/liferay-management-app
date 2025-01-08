package management.web.display.context;

import com.liferay.frontend.taglib.clay.servlet.taglib.util.CreationMenu;
import com.liferay.frontend.taglib.clay.servlet.taglib.util.SelectOption;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.LiferayWindowState;
import com.liferay.portal.kernel.portlet.url.builder.PortletURLBuilder;
import com.liferay.portal.kernel.service.UserLocalService;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.exception.NoSuchEmployeeException;
import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.EmployeeLocalServiceUtil;
import com.management.app.service.ManagerLocalServiceUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;

import management.web.constants.EmployeeRequestConstant;
import management.web.display.EmployeeDisplay;
import management.web.display.helper.ManagementRequestHelper;

/**
 * @author Albert Cabral
 */
public class ManagementDisplayContext {

    public ManagementDisplayContext(
            HttpServletRequest httpServletRequest, UserLocalService userLocalService) {

        this._httpServletRequest = httpServletRequest;
        this._userLocalService = userLocalService;

        this._managementRequestHelper = new ManagementRequestHelper(
                httpServletRequest);
    }

    public CreationMenu getCreationMenu() throws Exception {
        CreationMenu creationMenu = new CreationMenu();

        creationMenu.addDropdownItem(
                dropdownItem -> {
                    dropdownItem.setHref(getEmployeeDetailsRenderURL());
                    dropdownItem.setLabel("Employee Details");
                    dropdownItem.setTarget("modal-lg");
                });

        creationMenu.addDropdownItem(
                dropdownItem -> {
                    dropdownItem.setHref(getEmployeeRequestRenderURL());
                    dropdownItem.setLabel("Open a request to employee");
                    dropdownItem.setTarget("modal-lg");
                });

        return creationMenu;
    }

    public List<SelectOption> getEmployeeOptions(long selectedEmployeeRequestId) {
        List<SelectOption> selectOptions = new ArrayList<>();

        selectOptions.add(
            new SelectOption(
                "Benefit Education",
                String.valueOf(EmployeeRequestConstant.BENEFIT_EDUCATION),
                selectedEmployeeRequestId ==
                        EmployeeRequestConstant.BENEFIT_EDUCATION));

        selectOptions.add(
            new SelectOption(
                "Request promotion to employee",
                String.valueOf(EmployeeRequestConstant.PROMOTION),
                selectedEmployeeRequestId ==
                        EmployeeRequestConstant.PROMOTION));

        selectOptions.add(
            new SelectOption(
                "Request a new course to employee",
                String.valueOf(EmployeeRequestConstant.COURSE_TO_EMPLOYEE),
                selectedEmployeeRequestId ==
                        EmployeeRequestConstant.COURSE_TO_EMPLOYEE));

        selectOptions.add(
            new SelectOption(
                "Request employee's dismissal",
                String.valueOf(EmployeeRequestConstant.DISMISSAL_EMPLOYEE),
                selectedEmployeeRequestId ==
                        EmployeeRequestConstant.DISMISSAL_EMPLOYEE));

        return selectOptions;
    }

    public String getEmployeeDetailsRenderURL() throws Exception {
        return PortletURLBuilder.createRenderURL(
                _managementRequestHelper.getLiferayPortletResponse()
        ).setMVCRenderCommandName(
                "/management/view_details_employee"
        ).setWindowState(
                LiferayWindowState.POP_UP
        ).buildString();
    }

    public String getEmployeeRequestRenderURL() throws Exception {
        return PortletURLBuilder.createRenderURL(
                _managementRequestHelper.getLiferayPortletResponse()
        ).setMVCRenderCommandName(
                "/management/view_request_employee"
        ).setWindowState(
                LiferayWindowState.POP_UP
        ).buildString();
    }

    public EmployeeDisplay getEmployeeDisplay() throws PortalException {
        if (Objects.isNull(_employee)) {
            return EmployeeDisplay.of(_setEmployee(_httpServletRequest));
        }

        return EmployeeDisplay.of(_employee);
    }

    public long[] getSelectedRequestIds() {
        return EmployeeRequestConstant.getManagerRequestIds();
    }

    public boolean isManager() {
        try {
            Manager manager = ManagerLocalServiceUtil.findByCompanyIdAndEmployeeId(
                    _employee.getCompanyId(), _employee.getEmployeeId());

            return !Objects.isNull(manager);
        }
        catch (Exception exception) {
            return false;
        }
    }

    private Employee _setEmployee(HttpServletRequest httpServletRequest)
        throws PortalException {

        try {
            ThemeDisplay themeDisplay =
                (ThemeDisplay)
                    httpServletRequest.getAttribute(
                        WebKeys.THEME_DISPLAY);

            User user = _userLocalService.getUser(
                    themeDisplay.getUserId());

            if (user == null) {
                throw new NoSuchEmployeeException(
                    "Current user session not found");
            }

            Employee employee = EmployeeLocalServiceUtil.fetchEmployeeByUserId(
                user.getCompanyId(), user.getUserId());

            if (_employee == null) {
                _setEmployee(employee);

                return _employee;
            }
            else if (themeDisplay.isSignedIn() && user.isActive()) {
                _employee = EmployeeLocalServiceUtil.createEmployee(
                        0);

                _employee.setFirstName(user.getFirstName());
                _employee.setLastName(user.getLastName());
                _employee.setPosition("Administrator");
                _employee.setDepartment("Manager");

                return _employee;
            }
            else {
                throw new NoSuchEmployeeException(
                    "Unable to return employee accessing to user " +
                        user.getUserId());
            }
        }
        catch (NoSuchEmployeeException noSuchEmployeeException) {
            throw new NoSuchEmployeeException(
                "Unable to found employee in that request " +
                        httpServletRequest.getRequestURL(),
                noSuchEmployeeException);
        }

    }

    private void _setEmployee(Employee employee) {
        this._employee = employee;
    }

    private Employee _employee;
    private final HttpServletRequest _httpServletRequest;
    private final ManagementRequestHelper _managementRequestHelper;
    private final UserLocalService _userLocalService;

}
