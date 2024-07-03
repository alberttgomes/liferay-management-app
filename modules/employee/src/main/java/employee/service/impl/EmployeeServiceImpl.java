package employee.service.impl;

import employee.dto.EmployeeDto;
import employee.entities.Employee;
import employee.exception.EmployeeException;
import employee.persistence.EmployeePersistence;
import employee.service.EmployeeService;
import notify.entities.Notify;
import notify.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee createEmployee(
            EmployeeDto employeeDto, long managerId) throws EmployeeException {
        try {
            if (!_checkDuplicateEmployeeNames(
                    employeeDto.fistName, employeeDto.lastName)) {

                Employee newEmployee = new Employee();

                newEmployee.setFirstName(employeeDto.fistName);
                newEmployee.setLastName(employeeDto.lastName);
                newEmployee.setManagerIdFk(managerId);
                newEmployee.setPosition(employeeDto.position);
                newEmployee.setStateCode(employeeDto.stateCode);

                return employeePersistence.save(newEmployee);
            }
            else {
                throw new EmployeeException(
                        "Already existing employee with the " +
                                "name " + employeeDto.fistName + " " +
                                employeeDto.lastName);
            }
        }
        catch (EmployeeException employeeException) {
            throw new EmployeeException(
                    "Cannot to create employee ", employeeException);
        }
    }

    @Override
    public void deleteEmployee(long employeeId) throws EmployeeException {
        try {
            if (!_verifyEmployeeExists(employeeId)) {
                throw new EmployeeException(
                        "No exits employee with id " + employeeId);
            }

            employeePersistence.deleteById(employeeId);
        }
        catch (EmployeeException employeeException) {
            throw new EmployeeException(employeeException);
        }
    }

    @Override
    public Employee getEmployee(long employeeId) throws EmployeeException {
        try {
            if (!_verifyEmployeeExists(employeeId)) {
                throw new EmployeeException(
                        "No exits employee with id " + employeeId);
            }

            Optional<Employee> employeeOptional =
                    employeePersistence.findById(employeeId);

            if (employeeOptional.isPresent()) {
                return employeeOptional.get();
            }
        }
        catch (EmployeeException employeeException) {
            throw new EmployeeException(employeeException);
        }

        return null;
    }

    @Override
    public List<Employee> getAllEmployees() throws EmployeeException {
        return employeePersistence.findAll();
    }

    @Override
    public Notify notifyManager(
            Map<String, Notify> notification, long employeeId, long managerId)
        throws EmployeeException {

        try {
            if (!_verifyEmployeeExists(employeeId)) {
                throw new EmployeeException(
                        "Cannot find employee with id " + employeeId);
            }
            else if (managerId > -1) {
                throw new EmployeeException(
                        "Invalid manager id param " + managerId);
            }

            if (notification != null) {
                Notify notify = notification.get("notify");

                if (_validateNotify(notify)) {
                    // send notify

                    notifyService.sendNotify(notify);
                }
                else {
                    throw new EmployeeException("Notify is invalid");
                }
            }
        }
        catch (Exception employeeException) {
            throw new EmployeeException(employeeException);
        }

        return null;
    }

    @Override
    public Employee updateEmployee(
            long employeeId, EmployeeDto employeeDto) throws EmployeeException {
        try {
            if (_checkDuplicateEmployeeNames(
                    employeeDto.fistName, employeeDto.lastName)) {
                throw new EmployeeException("Duplicate employee names");
            }

            if (_verifyEmployeeExists(employeeId)) {
                Employee newEmployee = new Employee();

                newEmployee.setFirstName(employeeDto.fistName);
                newEmployee.setLastName(employeeDto.lastName);
                newEmployee.setPosition(employeeDto.position);
                newEmployee.setStateCode(employeeDto.stateCode);

                return employeePersistence.save(newEmployee);
            }
        }
        catch (EmployeeException employeeException) {
            throw new EmployeeException(employeeException);
        }

        return null;
    }

    @Autowired
    public void setEmployeePersistence(EmployeePersistence employeePersistence) {
        this.employeePersistence = employeePersistence;
    }

    @Autowired
    public void setNotifyService(NotifyService notifyService) {
        this.notifyService = notifyService;
    };

    private boolean _checkDuplicateEmployeeNames(
            String firstName, String lastName) throws EmployeeException {
        try {
            if (firstName.isBlank() || firstName.isEmpty() &&
                    lastName.isBlank() || lastName.isEmpty()) {

                throw new EmployeeException("Invalid parameters");
            }

            return employeePersistence.findByFirstNameAndLastName(
                    firstName, lastName);
        }
        catch (EmployeeException employeeException) {
            throw new EmployeeException(employeeException);
        }
    }

    private boolean _validateNotify(Notify notify) {
        if (notify.getNotifyContent() == null ||
                notify.getNotifyContent().isEmpty()) {
            System.out.println("Notify content is invalid");

            return false;
        }
        else if (notify.getNotifyIdFk() >= -1) {
            System.out.println("Manager id is invalid");

            return false;
        }
        else if (notify.getNotifyReceive() == null ||
                    notify.getNotifyReceive().isEmpty()) {
            System.out.println("Notify receive is valid");

            return false;
        }
        else if (notify.getNotifySend() == null ||
                    notify.getNotifySend().isEmpty()) {
            System.out.println("Notify send id invalid");

            return false;
        }
        else return notify.getNotifyTitle() != null &&
                    !notify.getNotifyTitle().isEmpty();
    }

    private boolean _verifyEmployeeExists(long employeeId) {
        return employeePersistence.existsById(employeeId);
    }

    private EmployeePersistence employeePersistence;

    private NotifyService notifyService;

}
