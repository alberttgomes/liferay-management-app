package management.web.model;

import java.util.Date;

/**
 * @author Albert Cabral
 */
public class EmployeeDetails {

    public EmployeeDetails(
            String department, String email, long employeeId, String name,
            int level, String position, Date startDate) {

        this._department = department;
        this._email = email;
        this._employeeId = employeeId;
        this._name = name;
        this._level = level;
        this._position = position;
        this._startDate = startDate;
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

    public int getLevel() {
        return _level;
    }

    public String getPosition() {
        return _position;
    }

    public Date getStartDate() {
        return _startDate;
    }

    private final String _department;
    private final String _email;
    private final long _employeeId;
    private final int _level;
    private final String _position;
    private final String _name;
    private final Date _startDate;

}
