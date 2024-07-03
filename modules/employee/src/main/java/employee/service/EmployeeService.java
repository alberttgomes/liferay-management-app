package employee.service;

import employee.dto.EmployeeDto;
import employee.entities.Employee;
import employee.exception.EmployeeException;
import notify.entities.Notify;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EmployeeService {

    /**
     *
     * @param EmployeeDto employee dto to be created
     * @param managerId manager id to be responsible to employee
     * @return new employee dto created
     * @throws EmployeeException employee exception if errors occur
     */
    Employee createEmployee(
            EmployeeDto EmployeeDto, long managerId) throws EmployeeException;

    /**
     *
     * @param employeeId employeeId to be deleted
     * @throws EmployeeException employee exception if errors occur
     */
    void deleteEmployee(long employeeId) throws EmployeeException;

    /**
     *
     * @param employeeId employee id to be found
     * @return employee entities
     * @throws EmployeeException employee exception if errors occur
     */
    Employee getEmployee(long employeeId) throws EmployeeException;

    /**
     *
     * @return all employees registered
     * @throws EmployeeException employee exception if errors occur
     */
    List<Employee> getAllEmployees() throws EmployeeException;

    /**
     *
     * @param notification notification hash map
     * @param employeeId employeeId that sand notification
     * @param managerId managerId manager that will be notified
     * @return notification object
     * @throws EmployeeException employee exception if errors occur
     */
    Notify notifyManager(
            Map<String, Notify> notification, long employeeId, long managerId)
        throws EmployeeException;

    /**
     *
     * @param employeeId employee id
     * @param employeeDto employee dto object
     * @return Employee updated
     * @throws EmployeeException employee exception if errors occur
     */
    Employee updateEmployee(
            long employeeId, EmployeeDto employeeDto) throws EmployeeException;

}
