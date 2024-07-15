package management.internal.graphql.servlet.v1_0;

import com.liferay.portal.kernel.util.ObjectValuePair;
import com.liferay.portal.vulcan.graphql.servlet.ServletData;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import management.internal.graphql.mutation.v1_0.Mutation;
import management.internal.graphql.query.v1_0.Query;
import management.internal.resource.v1_0.EmployeeResourceImpl;
import management.internal.resource.v1_0.ManagerResourceImpl;

import management.resource.v1_0.EmployeeResource;
import management.resource.v1_0.ManagerResource;

import org.osgi.framework.BundleContext;
import org.osgi.service.component.ComponentServiceObjects;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceScope;

/**
 * @author Albert Cabral
 * @generated
 */
@Component(service = ServletData.class)
@Generated("")
public class ServletDataImpl implements ServletData {

	@Activate
	public void activate(BundleContext bundleContext) {
		Mutation.setEmployeeResourceComponentServiceObjects(
			_employeeResourceComponentServiceObjects);
		Mutation.setManagerResourceComponentServiceObjects(
			_managerResourceComponentServiceObjects);

		Query.setEmployeeResourceComponentServiceObjects(
			_employeeResourceComponentServiceObjects);
		Query.setManagerResourceComponentServiceObjects(
			_managerResourceComponentServiceObjects);
	}

	public String getApplicationName() {
		return "Management";
	}

	@Override
	public Mutation getMutation() {
		return new Mutation();
	}

	@Override
	public String getPath() {
		return "/management-graphql/v1_0";
	}

	@Override
	public Query getQuery() {
		return new Query();
	}

	public ObjectValuePair<Class<?>, String> getResourceMethodObjectValuePair(
		String methodName, boolean mutation) {

		if (mutation) {
			return _resourceMethodObjectValuePairs.get(
				"mutation#" + methodName);
		}

		return _resourceMethodObjectValuePairs.get("query#" + methodName);
	}

	private static final Map<String, ObjectValuePair<Class<?>, String>>
		_resourceMethodObjectValuePairs =
			new HashMap<String, ObjectValuePair<Class<?>, String>>() {
				{
					put(
						"mutation#createEmployee",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class, "postEmployee"));
					put(
						"mutation#createEmployeeBatch",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class, "postEmployeeBatch"));
					put(
						"mutation#createEmployeeByManagerName",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class,
							"postEmployeeByManagerName"));
					put(
						"mutation#deleteEmployeeById",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class, "deleteEmployeeById"));
					put(
						"mutation#createManager",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "postManager"));
					put(
						"mutation#createManagerBatch",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "postManagerBatch"));
					put(
						"mutation#deleteManagerById",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "deleteManagerById"));
					put(
						"mutation#updateManagerById",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "updateManagerById"));

					put(
						"query#employees",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class, "getEmployees"));
					put(
						"query#employeeById",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class, "getEmployeeById"));
					put(
						"query#employeesByManagerId",
						new ObjectValuePair<>(
							EmployeeResourceImpl.class,
							"getEmployeesByManagerId"));
					put(
						"query#manager",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "getManager"));
					put(
						"query#manageById",
						new ObjectValuePair<>(
							ManagerResourceImpl.class, "getManageById"));
				}
			};

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<EmployeeResource>
		_employeeResourceComponentServiceObjects;

	@Reference(scope = ReferenceScope.PROTOTYPE_REQUIRED)
	private ComponentServiceObjects<ManagerResource>
		_managerResourceComponentServiceObjects;

}