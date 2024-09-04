package management.internal.graphql.query.v1_0;

import com.liferay.petra.function.UnsafeConsumer;
import com.liferay.petra.function.UnsafeFunction;
import com.liferay.portal.kernel.search.Sort;
import com.liferay.portal.kernel.search.filter.Filter;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.RoleLocalService;
import com.liferay.portal.vulcan.accept.language.AcceptLanguage;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.pagination.Page;

import java.util.Map;
import java.util.function.BiFunction;

import javax.annotation.Generated;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.ws.rs.core.UriInfo;

import management.dto.v1_0.Employee;
import management.dto.v1_0.Manager;

import management.resource.v1_0.EmployeeResource;
import management.resource.v1_0.ManagerResource;

import org.osgi.service.component.ComponentServiceObjects;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
public class Query {

	public static void setEmployeeResourceComponentServiceObjects(
		ComponentServiceObjects<EmployeeResource>
			employeeResourceComponentServiceObjects) {

		_employeeResourceComponentServiceObjects =
			employeeResourceComponentServiceObjects;
	}

	public static void setManagerResourceComponentServiceObjects(
		ComponentServiceObjects<ManagerResource>
			managerResourceComponentServiceObjects) {

		_managerResourceComponentServiceObjects =
			managerResourceComponentServiceObjects;
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {employees{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EmployeePage employees() throws Exception {
		return _applyComponentServiceObjects(
			_employeeResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeResource -> new EmployeePage(
				employeeResource.getEmployees()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {employeeById(employeeId: ___){employeeId, companyId, department, firstName, groupId, lastName, position, isManager, level, managerIdPK, stateCode, status, userId}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public Employee employeeById(@GraphQLName("employeeId") Integer employeeId)
		throws Exception {

		return _applyComponentServiceObjects(
			_employeeResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeResource -> employeeResource.getEmployeeById(employeeId));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {employeesByManagerId(managerId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public EmployeePage employeesByManagerId(
			@GraphQLName("managerId") Integer managerId)
		throws Exception {

		return _applyComponentServiceObjects(
			_employeeResourceComponentServiceObjects,
			this::_populateResourceContext,
			employeeResource -> new EmployeePage(
				employeeResource.getEmployeesByManagerId(managerId)));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {manager{items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ManagerPage manager() throws Exception {
		return _applyComponentServiceObjects(
			_managerResourceComponentServiceObjects,
			this::_populateResourceContext,
			managerResource -> new ManagerPage(managerResource.getManager()));
	}

	/**
	 * Invoke this method with the command line:
	 *
	 * curl -H 'Content-Type: text/plain; charset=utf-8' -X 'POST' 'http://localhost:8080/o/graphql' -d $'{"query": "query {manageById(managerId: ___){items {__}, page, pageSize, totalCount}}"}' -u 'test@liferay.com:test'
	 */
	@GraphQLField
	public ManagerPage manageById(@GraphQLName("managerId") Integer managerId)
		throws Exception {

		return _applyComponentServiceObjects(
			_managerResourceComponentServiceObjects,
			this::_populateResourceContext,
			managerResource -> new ManagerPage(
				managerResource.getManageById(managerId)));
	}

	@GraphQLName("EmployeePage")
	public class EmployeePage {

		public EmployeePage(Page employeePage) {
			actions = employeePage.getActions();

			items = employeePage.getItems();
			lastPage = employeePage.getLastPage();
			page = employeePage.getPage();
			pageSize = employeePage.getPageSize();
			totalCount = employeePage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Employee> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	@GraphQLName("ManagerPage")
	public class ManagerPage {

		public ManagerPage(Page managerPage) {
			actions = managerPage.getActions();

			items = managerPage.getItems();
			lastPage = managerPage.getLastPage();
			page = managerPage.getPage();
			pageSize = managerPage.getPageSize();
			totalCount = managerPage.getTotalCount();
		}

		@GraphQLField
		protected Map<String, Map<String, String>> actions;

		@GraphQLField
		protected java.util.Collection<Manager> items;

		@GraphQLField
		protected long lastPage;

		@GraphQLField
		protected long page;

		@GraphQLField
		protected long pageSize;

		@GraphQLField
		protected long totalCount;

	}

	private <T, R, E1 extends Throwable, E2 extends Throwable> R
			_applyComponentServiceObjects(
				ComponentServiceObjects<T> componentServiceObjects,
				UnsafeConsumer<T, E1> unsafeConsumer,
				UnsafeFunction<T, R, E2> unsafeFunction)
		throws E1, E2 {

		T resource = componentServiceObjects.getService();

		try {
			unsafeConsumer.accept(resource);

			return unsafeFunction.apply(resource);
		}
		finally {
			componentServiceObjects.ungetService(resource);
		}
	}

	private void _populateResourceContext(EmployeeResource employeeResource)
		throws Exception {

		employeeResource.setContextAcceptLanguage(_acceptLanguage);
		employeeResource.setContextCompany(_company);
		employeeResource.setContextHttpServletRequest(_httpServletRequest);
		employeeResource.setContextHttpServletResponse(_httpServletResponse);
		employeeResource.setContextUriInfo(_uriInfo);
		employeeResource.setContextUser(_user);
		employeeResource.setGroupLocalService(_groupLocalService);
		employeeResource.setRoleLocalService(_roleLocalService);
	}

	private void _populateResourceContext(ManagerResource managerResource)
		throws Exception {

		managerResource.setContextAcceptLanguage(_acceptLanguage);
		managerResource.setContextCompany(_company);
		managerResource.setContextHttpServletRequest(_httpServletRequest);
		managerResource.setContextHttpServletResponse(_httpServletResponse);
		managerResource.setContextUriInfo(_uriInfo);
		managerResource.setContextUser(_user);
		managerResource.setGroupLocalService(_groupLocalService);
		managerResource.setRoleLocalService(_roleLocalService);
	}

	private static ComponentServiceObjects<EmployeeResource>
		_employeeResourceComponentServiceObjects;
	private static ComponentServiceObjects<ManagerResource>
		_managerResourceComponentServiceObjects;

	private AcceptLanguage _acceptLanguage;
	private com.liferay.portal.kernel.model.Company _company;
	private BiFunction<Object, String, Filter> _filterBiFunction;
	private GroupLocalService _groupLocalService;
	private HttpServletRequest _httpServletRequest;
	private HttpServletResponse _httpServletResponse;
	private RoleLocalService _roleLocalService;
	private BiFunction<Object, String, Sort[]> _sortsBiFunction;
	private UriInfo _uriInfo;
	private com.liferay.portal.kernel.model.User _user;

}