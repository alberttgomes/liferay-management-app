package management.web.display;

import com.liferay.petra.string.StringPool;
import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.*;

import com.management.app.model.Employee;
import com.management.app.service.EmployeeLocalServiceUtil;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Albert Cabral
 */
public class EmployeeDisplay {

    public static EmployeeDisplay of(Employee employee) {
        if (employee != null) {
            return new EmployeeDisplay(employee);
        }

        return _EMPTY_INSTANCE;
    }

    public static EmployeeDisplay of(long employeeId) {
        return of(EmployeeLocalServiceUtil.fetchEmployee(employeeId));
    }

    public long getEmployeeId() {
        return _employeeId;
    }

    public String getDepartment() {
        return _department;
    }

    public String getFullName() {
        return _fullName;
    }

    public String getPosition() {
        return _position;
    }

    public String getScreenName() {
        return _screenName;
    }

    public String getType(Locale locale) {
        ResourceBundle resourceBundle = ResourceBundleUtil.getBundle(
                "content.Language", locale, getClass());

        return LanguageUtil.get(
                new AggregateResourceBundle(
                        resourceBundle, PortalUtil.getResourceBundle(locale)),
                "language");
    }

    private EmployeeDisplay() {
        _employeeId = 0;
        _department = StringPool.BLANK;
        _fullName = StringPool.BLANK;
        _position = StringPool.BLANK;
        _screenName = StringPool.BLANK;
    }

    private EmployeeDisplay(Employee employee) {
        _employeeId = employee.getEmployeeId();
        _department = employee.getDepartment();
        _fullName = employee.getFirstName() + StringPool.SPACE + employee.getLastName();
        _position = employee.getPosition();
        _screenName = _buildScreenName(
                employee.getFirstName(), employee.getLastName(), _employeeId);
    }

    private String _buildScreenName(
            String firstName, String lastName, long employeeId) {
        String screeNameFormated = firstName + lastName +
                StringPool.UNDERLINE + employeeId;

        return screeNameFormated.toLowerCase();
    }

    private static final EmployeeDisplay _EMPTY_INSTANCE = new EmployeeDisplay();

    private final long _employeeId;
    private final String _department;
    private final String _fullName;
    private final String _position;
    private final String _screenName;

}
