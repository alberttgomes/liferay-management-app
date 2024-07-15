package management.internal.dto.v1_0.converter;

import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import com.management.app.service.EmployeeLocalService;
import management.dto.v1_0.Employee;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
        property = "dto.class.name=com.management.app.model.Employee",
        service = DTOConverter.class
)
public class EmployeeResourceDTOConverter
        implements DTOConverter<com.management.app.model.Employee, Employee> {

    @Override
    public String getContentType() {
        return Employee.class.getSimpleName();
    }

    @Override
    public Employee toDTO(DTOConverterContext dtoConverterContext)
        throws Exception {
        com.management.app.model.Employee employee =
                _employeeLocalService.getEmployee((Long)dtoConverterContext.getId());

        return new Employee() {
            {
                department = employee.getDepartment();
                employeeId = employee.getEmployeeId();
                firstName = employee.getFirstName();
                lastName = employee.getLastName();
                position = employee.getPosition();
                level = (long) employee.getLevel();
                isManager = employee.getIsManager();
                managerIdPK = employee.getManagerIdPK();
                stateCode = employee.getStateCode();
                status = (long) employee.getStatus();
                userId = employee.getUserId();
            }
        };
    }

    @Reference
    private EmployeeLocalService _employeeLocalService;

}
