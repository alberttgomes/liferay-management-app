package management.internal.dto.v1_0.converter;

import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;

import management.dto.v1_0.Employee;

import org.osgi.service.component.annotations.Component;

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
    public Employee toDTO(
            DTOConverterContext dtoConverterContext,
            com.management.app.model.Employee employee) throws Exception {

        return new Employee() {
            {
                setDepartment(employee::getDepartment);
                setEmployeeId(employee::getEmployeeId);
                setFirstName(employee::getFirstName);
                setLastName(employee::getLastName);
                setPosition(employee::getPosition);
            }
        };
    }

}
