package management.web.internal.frontend.data.set.provider;

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
import java.util.Objects;

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

        if (Objects.isNull(manager)) {
            return new ArrayList<>();
        }

        long companyId = themeDisplay.getCompanyId();
        long employeeId = manager.getEmployeeIdPK();

        for (Employee employee : _employeeLocalService.getAllEmployeeByManager(
                employeeId, companyId, true)) {

            User user = UserLocalServiceUtil.getUser(
                    employee.getUserId());

            String name = user.getFullName();

            employeeDetails.add(
                new EmployeeDetails(
                    employee.getDepartment(),
                    user.getEmailAddress(),
                    employee.getEmployeeId(), name,
                    employee.getLevel(),
                    employee.getPosition(),
                    employee.getCreateDate()));
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

        if (Objects.isNull(user)) {
            return null;
        }
        else {
            Employee employee = _employeeLocalService.fetchEmployeeByUserId(
                    user.getCompanyId(), user.getUserId());

            if (Objects.isNull(employee)) {
                return _managerLocalService.fetchManagerByUuidAndGroupId(
                            user.getUuid(), user.getGroupId());
            }

            return _managerLocalService.findByCompanyIdAndEmployeeId(
                    employee.getCompanyId(), employee.getEmployeeId());
        }
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

    @Reference
    private ManagerLocalService _managerLocalService;

}
