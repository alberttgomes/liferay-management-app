package employee.entities;

import javax.persistence.*;

@Entity
public class Employee {

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public long getManagerIdFk() {
        return managerIdFk;
    }

    public void setManagerIdFk(long managerIdFk) {
        this.managerIdFk = managerIdFk;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long employeeId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String position;

    @Column(nullable = false)
    private long managerIdFk;

    @Column(nullable = false, length = 5)
    private String stateCode;

}
