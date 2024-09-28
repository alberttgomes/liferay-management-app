package management.web.frontend.data.set.provider;

import com.liferay.frontend.data.set.provider.FDSDataProvider;
import com.liferay.frontend.data.set.provider.search.FDSKeywords;
import com.liferay.frontend.data.set.provider.search.FDSPagination;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;

import com.management.app.model.Employee;
import com.management.app.model.Manager;
import com.management.app.service.EmployeeLocalService;
import com.management.app.service.ManagerLocalService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import management.web.constants.EmployeeDetailsFDSNames;
import management.web.model.EmployeeDetails;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
        property = "fds.data.provider.key=" + EmployeeDetailsFDSNames.EMPLOYEE_DETAILS,
        service = FDSDataProvider.class
)
public class EmployeeDetailsFDSDataProvider
        implements FDSDataProvider<EmployeeDetails> {

    @Override
    public List<EmployeeDetails> getItems(
            FDSKeywords fdsKeywords, FDSPagination fdsPagination,
            HttpServletRequest httpServletRequest, Sort sort)
        throws PortalException {

        List<EmployeeDetails> employeeDetails = new ArrayList<>();

        ThemeDisplay themeDisplay =
                (ThemeDisplay) httpServletRequest.getAttribute(
                        WebKeys.THEME_DISPLAY);

        Manager manager = _getManagerIdByCurrentUser(themeDisplay);

        assert manager != null;

        List<Employee> employees = _employeeLocalService.getAllEmployeeByManager(
                manager.getManagerId(), themeDisplay.getCompanyId(), true);

        for (Employee employee : employees) {

            User user = UserLocalServiceUtil.getUser(
                    employee.getUserId());

            String name = user.getFullName();

            employeeDetails.add(
                    new EmployeeDetails(
                            employee.getDepartment(), user.getEmailAddress(),
                            employee.getEmployeeId(), name, employee.getPosition()));
        }

        return employeeDetails;
    }

    @Override
    public int getItemsCount(
            FDSKeywords fdsKeywords, HttpServletRequest httpServletRequest)
        throws PortalException {

        return _employeeLocalService.getEmployeesCount();
    }

    private Manager _getManagerIdByCurrentUser(ThemeDisplay themeDisplay)
        throws PortalException {

        User user = themeDisplay.getUser();

        if (user == null) {
            return null;
        }
        else {
            Employee employee = _employeeLocalService.fetchEmployeeByUserId(
                    user.getCompanyId(), user.getUserId());

            if (employee == null) {
                return _managerLocalService.fetchManagerByUuidAndGroupId(
                        user.getUuid(), user.getGroupId());
            }

            return _managerLocalService.getManager(
                    employee.getManagerIdFK());
        }
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private ManagerLocalService _managerLocalService;

}
