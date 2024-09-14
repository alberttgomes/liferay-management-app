package management.internal.resource.v1_0;

import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.vulcan.dto.converter.DTOConverter;
import com.liferay.portal.vulcan.pagination.Page;

import com.management.app.service.EmployeeLocalService;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.Response;

import management.dto.v1_0.Employee;
import management.internal.dto.v1_0.converter.constants.DTOConverterConstants;
import management.resource.v1_0.EmployeeResource;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ServiceScope;

/**
 * @author Albert Cabral
 */
@Component(
	properties = "OSGI-INF/liferay/rest/v1_0/employee.properties",
	scope = ServiceScope.PROTOTYPE, service = EmployeeResource.class
)
public class EmployeeResourceImpl extends BaseEmployeeResourceImpl {

	@Override
	public Response deleteEmployeeById(Integer employeeId) throws Exception {
		com.management.app.model.Employee employee =
				_employeeLocalService.deleteEmployee(employeeId);

		if (employee == null) {
			return Response.status(Response.Status.NOT_FOUND).build();
		}

		return Response.ok().build();
	}

	@Override
	public Page<Employee> getEmployees() throws Exception {
		List<com.management.app.model.Employee> baseEmployeeModelResult =
				_employeeLocalService.getEmployees(
						QueryUtil.ALL_POS, QueryUtil.ALL_POS);

		return Page.of(new ArrayList<>());
	}

	@Override
	public Employee getEmployeeById(Integer employeeId) throws Exception {
		return _employeeResourceDTOConverter.toDTO(
				_employeeLocalService.getEmployee(employeeId));
	}

	@Override
	public Employee postEmployee(Employee employee) throws Exception {
		User user = PortalUtil.getUser(contextHttpServletRequest);

		int status = Math.toIntExact(employee.getStatus());
		int level = Math.toIntExact(employee.getLevel());

		return _employeeResourceDTOConverter.toDTO(
				_employeeLocalService.addEmployee(
						employee.getFirstName(), employee.getLastName(),
						employee.getDepartment(), employee.getPosition(),
						level, employee.getStateCode(), status,
						0, employee.getIsManager(), user, 0));
	}

	@Override
	public Employee promotingEmployee(
			String position, String department, int level, long employeeId,
			boolean isManager) throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)
				contextHttpServletRequest.getAttribute(
						WebKeys.THEME_DISPLAY);

		return _employeeResourceDTOConverter.toDTO(
				_employeeLocalService.employeePromoting(
						position, themeDisplay.getUserId(), department, level,
						employeeId, isManager));
	}

	@Reference(target = DTOConverterConstants.EMPLOYEE_RESOURCE_DTO_CONVERTER)
	private DTOConverter<com.management.app.model.Employee, Employee>
			_employeeResourceDTOConverter;

	@Reference
	private EmployeeLocalService _employeeLocalService;

}