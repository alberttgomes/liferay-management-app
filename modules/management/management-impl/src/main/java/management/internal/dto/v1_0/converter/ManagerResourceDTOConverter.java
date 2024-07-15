package management.internal.dto.v1_0.converter;

import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.dto.converter.DTOConverterContext;
import com.management.app.service.ManagerLocalService;
import management.dto.v1_0.Manager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * @author Albert Cabral
 */
@Component(
        property = "dto.class.name=com.management.app.model.Manager",
        service = DTOConverter.class
)
public class ManagerResourceDTOConverter implements DTOConverter<com.management.app.model.Manager, Manager> {

    @Override
    public String getContentType() {
        return Manager.class.getSimpleName();
    }

    @Override
    public Manager toDTO(
            DTOConverterContext dtoConverterContext,
            com.management.app.model.Manager serviceBuilderManager)
        throws Exception {

        return new Manager() {
            {
                employeeIdPK = serviceBuilderManager.getEmployeeIdPK();
                managerId = serviceBuilderManager.getManagerId();
            }
        };
    }

    @Reference
    private ManagerLocalService _managerLocalService;

}
