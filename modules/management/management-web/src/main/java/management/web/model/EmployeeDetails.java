package management.web.model;

/**
 * @author Albert Cabral
 */
public class EmployeeDetails {

    public EmployeeDetails(
            String department, String email, long employeeId, String name,
            String position) {

        this._department = department;
        this._email = email;
        this._employeeId = employeeId;
        this._name = name;
        this._position = position;
    }

    public String getDepartment() {
        return _department;
    }

    public String getEmail() {
        return _email;
    }

    public long getEmployeeId() {
        return _employeeId;
    }

    public String getFullName() {
        return _name;
    }

    public String getPosition() {
        return _position;
    }

    private final String _department;
    private final String _email;
    private final long _employeeId;
    private final String _name;
    private final String _position;

}
