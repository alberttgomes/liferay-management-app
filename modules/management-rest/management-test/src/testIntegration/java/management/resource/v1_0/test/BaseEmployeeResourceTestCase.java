package management.resource.v1_0.test;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import com.liferay.petra.reflect.ReflectionUtil;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.json.JSONUtil;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.service.CompanyLocalServiceUtil;
import com.liferay.portal.kernel.test.util.GroupTestUtil;
import com.liferay.portal.kernel.test.util.RandomTestUtil;
import com.liferay.portal.kernel.util.ArrayUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.odata.entity.EntityField;
import com.liferay.portal.odata.entity.EntityModel;
import com.liferay.portal.test.rule.Inject;
import com.liferay.portal.test.rule.LiferayIntegrationTestRule;
import com.liferay.portal.vulcan.resource.EntityModelResource;
import com.liferay.portal.vulcan.util.TransformUtil;

import java.lang.reflect.Method;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import javax.annotation.Generated;

import javax.ws.rs.core.MultivaluedHashMap;

import management.client.dto.v1_0.Employee;
import management.client.http.HttpInvoker;
import management.client.pagination.Page;
import management.client.resource.v1_0.EmployeeResource;
import management.client.serdes.v1_0.EmployeeSerDes;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
public abstract class BaseEmployeeResourceTestCase {

	@ClassRule
	@Rule
	public static final LiferayIntegrationTestRule liferayIntegrationTestRule =
		new LiferayIntegrationTestRule();

	@BeforeClass
	public static void setUpClass() throws Exception {
		_dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss'Z'");
	}

	@Before
	public void setUp() throws Exception {
		irrelevantGroup = GroupTestUtil.addGroup();
		testGroup = GroupTestUtil.addGroup();

		testCompany = CompanyLocalServiceUtil.getCompany(
			testGroup.getCompanyId());

		_employeeResource.setContextCompany(testCompany);

		EmployeeResource.Builder builder = EmployeeResource.builder();

		employeeResource = builder.authentication(
			"test@liferay.com", "test"
		).locale(
			LocaleUtil.getDefault()
		).build();
	}

	@After
	public void tearDown() throws Exception {
		GroupTestUtil.deleteGroup(irrelevantGroup);
		GroupTestUtil.deleteGroup(testGroup);
	}

	@Test
	public void testClientSerDesToDTO() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				enable(SerializationFeature.INDENT_OUTPUT);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Employee employee1 = randomEmployee();

		String json = objectMapper.writeValueAsString(employee1);

		Employee employee2 = EmployeeSerDes.toDTO(json);

		Assert.assertTrue(equals(employee1, employee2));
	}

	@Test
	public void testClientSerDesToJSON() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper() {
			{
				configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY, true);
				configure(
					SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true);
				setDateFormat(new ISO8601DateFormat());
				setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
				setSerializationInclusion(JsonInclude.Include.NON_NULL);
				setVisibility(
					PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
				setVisibility(
					PropertyAccessor.GETTER, JsonAutoDetect.Visibility.NONE);
			}
		};

		Employee employee = randomEmployee();

		String json1 = objectMapper.writeValueAsString(employee);
		String json2 = EmployeeSerDes.toJSON(employee);

		Assert.assertEquals(
			objectMapper.readTree(json1), objectMapper.readTree(json2));
	}

	@Test
	public void testEscapeRegexInStringFields() throws Exception {
		String regex = "^[0-9]+(\\.[0-9]{1,2})\"?";

		Employee employee = randomEmployee();

		employee.setDepartment(regex);
		employee.setFirstName(regex);
		employee.setLastName(regex);
		employee.setPosition(regex);
		employee.setStateCode(regex);

		String json = EmployeeSerDes.toJSON(employee);

		Assert.assertFalse(json.contains(regex));

		employee = EmployeeSerDes.toDTO(json);

		Assert.assertEquals(regex, employee.getDepartment());
		Assert.assertEquals(regex, employee.getFirstName());
		Assert.assertEquals(regex, employee.getLastName());
		Assert.assertEquals(regex, employee.getPosition());
		Assert.assertEquals(regex, employee.getStateCode());
	}

	@Test
	public void testGetEmployees() throws Exception {
		Page<Employee> page = employeeResource.getEmployees();

		long totalCount = page.getTotalCount();

		Employee employee1 = testGetEmployees_addEmployee(randomEmployee());

		Employee employee2 = testGetEmployees_addEmployee(randomEmployee());

		page = employeeResource.getEmployees();

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(employee1, (List<Employee>)page.getItems());
		assertContains(employee2, (List<Employee>)page.getItems());
		assertValid(page, testGetEmployees_getExpectedActions());
	}

	protected Map<String, Map<String, String>>
			testGetEmployees_getExpectedActions()
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected Employee testGetEmployees_addEmployee(Employee employee)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testGraphQLGetEmployees() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testPostEmployee() throws Exception {
		Employee randomEmployee = randomEmployee();

		Employee postEmployee = testPostEmployee_addEmployee(randomEmployee);

		assertEquals(randomEmployee, postEmployee);
		assertValid(postEmployee);
	}

	protected Employee testPostEmployee_addEmployee(Employee employee)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testPostEmployeeByManagerName() throws Exception {
		Employee randomEmployee = randomEmployee();

		Employee postEmployee = testPostEmployeeByManagerName_addEmployee(
			randomEmployee);

		assertEquals(randomEmployee, postEmployee);
		assertValid(postEmployee);
	}

	protected Employee testPostEmployeeByManagerName_addEmployee(
			Employee employee)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	@Test
	public void testDeleteEmployeeById() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGetEmployeeById() throws Exception {
		Assert.assertTrue(false);
	}

	@Test
	public void testGraphQLGetEmployeeById() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGraphQLGetEmployeeByIdNotFound() throws Exception {
		Assert.assertTrue(true);
	}

	@Test
	public void testGetEmployeesByManagerId() throws Exception {
		Integer managerId = testGetEmployeesByManagerId_getManagerId();
		Integer irrelevantManagerId =
			testGetEmployeesByManagerId_getIrrelevantManagerId();

		Page<Employee> page = employeeResource.getEmployeesByManagerId(
			managerId);

		long totalCount = page.getTotalCount();

		if (irrelevantManagerId != null) {
			Employee irrelevantEmployee =
				testGetEmployeesByManagerId_addEmployee(
					irrelevantManagerId, randomIrrelevantEmployee());

			page = employeeResource.getEmployeesByManagerId(
				irrelevantManagerId);

			Assert.assertEquals(totalCount + 1, page.getTotalCount());

			assertContains(irrelevantEmployee, (List<Employee>)page.getItems());
			assertValid(
				page,
				testGetEmployeesByManagerId_getExpectedActions(
					irrelevantManagerId));
		}

		Employee employee1 = testGetEmployeesByManagerId_addEmployee(
			managerId, randomEmployee());

		Employee employee2 = testGetEmployeesByManagerId_addEmployee(
			managerId, randomEmployee());

		page = employeeResource.getEmployeesByManagerId(managerId);

		Assert.assertEquals(totalCount + 2, page.getTotalCount());

		assertContains(employee1, (List<Employee>)page.getItems());
		assertContains(employee2, (List<Employee>)page.getItems());
		assertValid(
			page, testGetEmployeesByManagerId_getExpectedActions(managerId));
	}

	protected Map<String, Map<String, String>>
			testGetEmployeesByManagerId_getExpectedActions(Integer managerId)
		throws Exception {

		Map<String, Map<String, String>> expectedActions = new HashMap<>();

		return expectedActions;
	}

	protected Employee testGetEmployeesByManagerId_addEmployee(
			Integer managerId, Employee employee)
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Integer testGetEmployeesByManagerId_getManagerId()
		throws Exception {

		throw new UnsupportedOperationException(
			"This method needs to be implemented");
	}

	protected Integer testGetEmployeesByManagerId_getIrrelevantManagerId()
		throws Exception {

		return null;
	}

	protected void assertContains(Employee employee, List<Employee> employees) {
		boolean contains = false;

		for (Employee item : employees) {
			if (equals(employee, item)) {
				contains = true;

				break;
			}
		}

		Assert.assertTrue(
			employees + " does not contain " + employee, contains);
	}

	protected void assertHttpResponseStatusCode(
		int expectedHttpResponseStatusCode,
		HttpInvoker.HttpResponse actualHttpResponse) {

		Assert.assertEquals(
			expectedHttpResponseStatusCode, actualHttpResponse.getStatusCode());
	}

	protected void assertEquals(Employee employee1, Employee employee2) {
		Assert.assertTrue(
			employee1 + " does not equal " + employee2,
			equals(employee1, employee2));
	}

	protected void assertEquals(
		List<Employee> employees1, List<Employee> employees2) {

		Assert.assertEquals(employees1.size(), employees2.size());

		for (int i = 0; i < employees1.size(); i++) {
			Employee employee1 = employees1.get(i);
			Employee employee2 = employees2.get(i);

			assertEquals(employee1, employee2);
		}
	}

	protected void assertEqualsIgnoringOrder(
		List<Employee> employees1, List<Employee> employees2) {

		Assert.assertEquals(employees1.size(), employees2.size());

		for (Employee employee1 : employees1) {
			boolean contains = false;

			for (Employee employee2 : employees2) {
				if (equals(employee1, employee2)) {
					contains = true;

					break;
				}
			}

			Assert.assertTrue(
				employees2 + " does not contain " + employee1, contains);
		}
	}

	protected void assertValid(Employee employee) throws Exception {
		boolean valid = true;

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("companyId", additionalAssertFieldName)) {
				if (employee.getCompanyId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("department", additionalAssertFieldName)) {
				if (employee.getDepartment() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("employeeId", additionalAssertFieldName)) {
				if (employee.getEmployeeId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("firstName", additionalAssertFieldName)) {
				if (employee.getFirstName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("groupId", additionalAssertFieldName)) {
				if (employee.getGroupId() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("isManager", additionalAssertFieldName)) {
				if (employee.getIsManager() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("lastName", additionalAssertFieldName)) {
				if (employee.getLastName() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("level", additionalAssertFieldName)) {
				if (employee.getLevel() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("managerIdPK", additionalAssertFieldName)) {
				if (employee.getManagerIdPK() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("position", additionalAssertFieldName)) {
				if (employee.getPosition() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("stateCode", additionalAssertFieldName)) {
				if (employee.getStateCode() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (employee.getStatus() == null) {
					valid = false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (employee.getUserId() == null) {
					valid = false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		Assert.assertTrue(valid);
	}

	protected void assertValid(Page<Employee> page) {
		assertValid(page, Collections.emptyMap());
	}

	protected void assertValid(
		Page<Employee> page, Map<String, Map<String, String>> expectedActions) {

		boolean valid = false;

		java.util.Collection<Employee> employees = page.getItems();

		int size = employees.size();

		if ((page.getLastPage() > 0) && (page.getPage() > 0) &&
			(page.getPageSize() > 0) && (page.getTotalCount() > 0) &&
			(size > 0)) {

			valid = true;
		}

		Assert.assertTrue(valid);

		assertValid(page.getActions(), expectedActions);
	}

	protected void assertValid(
		Map<String, Map<String, String>> actions1,
		Map<String, Map<String, String>> actions2) {

		for (String key : actions2.keySet()) {
			Map action = actions1.get(key);

			Assert.assertNotNull(key + " does not contain an action", action);

			Map<String, String> expectedAction = actions2.get(key);

			Assert.assertEquals(
				expectedAction.get("method"), action.get("method"));
			Assert.assertEquals(expectedAction.get("href"), action.get("href"));
		}
	}

	protected String[] getAdditionalAssertFieldNames() {
		return new String[0];
	}

	protected List<GraphQLField> getGraphQLFields() throws Exception {
		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field :
				getDeclaredFields(management.dto.v1_0.Employee.class)) {

			if (!ArrayUtil.contains(
					getAdditionalAssertFieldNames(), field.getName())) {

				continue;
			}

			graphQLFields.addAll(getGraphQLFields(field));
		}

		return graphQLFields;
	}

	protected List<GraphQLField> getGraphQLFields(
			java.lang.reflect.Field... fields)
		throws Exception {

		List<GraphQLField> graphQLFields = new ArrayList<>();

		for (java.lang.reflect.Field field : fields) {
			com.liferay.portal.vulcan.graphql.annotation.GraphQLField
				vulcanGraphQLField = field.getAnnotation(
					com.liferay.portal.vulcan.graphql.annotation.GraphQLField.
						class);

			if (vulcanGraphQLField != null) {
				Class<?> clazz = field.getType();

				if (clazz.isArray()) {
					clazz = clazz.getComponentType();
				}

				List<GraphQLField> childrenGraphQLFields = getGraphQLFields(
					getDeclaredFields(clazz));

				graphQLFields.add(
					new GraphQLField(field.getName(), childrenGraphQLFields));
			}
		}

		return graphQLFields;
	}

	protected String[] getIgnoredEntityFieldNames() {
		return new String[0];
	}

	protected boolean equals(Employee employee1, Employee employee2) {
		if (employee1 == employee2) {
			return true;
		}

		for (String additionalAssertFieldName :
				getAdditionalAssertFieldNames()) {

			if (Objects.equals("companyId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getCompanyId(), employee2.getCompanyId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("department", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getDepartment(), employee2.getDepartment())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("employeeId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getEmployeeId(), employee2.getEmployeeId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("firstName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getFirstName(), employee2.getFirstName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("groupId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getGroupId(), employee2.getGroupId())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("isManager", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getIsManager(), employee2.getIsManager())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("lastName", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getLastName(), employee2.getLastName())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("level", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getLevel(), employee2.getLevel())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("managerIdPK", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getManagerIdPK(),
						employee2.getManagerIdPK())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("position", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getPosition(), employee2.getPosition())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("stateCode", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getStateCode(), employee2.getStateCode())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("status", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getStatus(), employee2.getStatus())) {

					return false;
				}

				continue;
			}

			if (Objects.equals("userId", additionalAssertFieldName)) {
				if (!Objects.deepEquals(
						employee1.getUserId(), employee2.getUserId())) {

					return false;
				}

				continue;
			}

			throw new IllegalArgumentException(
				"Invalid additional assert field name " +
					additionalAssertFieldName);
		}

		return true;
	}

	protected boolean equals(
		Map<String, Object> map1, Map<String, Object> map2) {

		if (Objects.equals(map1.keySet(), map2.keySet())) {
			for (Map.Entry<String, Object> entry : map1.entrySet()) {
				if (entry.getValue() instanceof Map) {
					if (!equals(
							(Map)entry.getValue(),
							(Map)map2.get(entry.getKey()))) {

						return false;
					}
				}
				else if (!Objects.deepEquals(
							entry.getValue(), map2.get(entry.getKey()))) {

					return false;
				}
			}

			return true;
		}

		return false;
	}

	protected java.lang.reflect.Field[] getDeclaredFields(Class clazz)
		throws Exception {

		if (clazz.getClassLoader() == null) {
			return new java.lang.reflect.Field[0];
		}

		return TransformUtil.transform(
			ReflectionUtil.getDeclaredFields(clazz),
			field -> {
				if (field.isSynthetic()) {
					return null;
				}

				return field;
			},
			java.lang.reflect.Field.class);
	}

	protected java.util.Collection<EntityField> getEntityFields()
		throws Exception {

		if (!(_employeeResource instanceof EntityModelResource)) {
			throw new UnsupportedOperationException(
				"Resource is not an instance of EntityModelResource");
		}

		EntityModelResource entityModelResource =
			(EntityModelResource)_employeeResource;

		EntityModel entityModel = entityModelResource.getEntityModel(
			new MultivaluedHashMap());

		if (entityModel == null) {
			return Collections.emptyList();
		}

		Map<String, EntityField> entityFieldsMap =
			entityModel.getEntityFieldsMap();

		return entityFieldsMap.values();
	}

	protected List<EntityField> getEntityFields(EntityField.Type type)
		throws Exception {

		return TransformUtil.transform(
			getEntityFields(),
			entityField -> {
				if (!Objects.equals(entityField.getType(), type) ||
					ArrayUtil.contains(
						getIgnoredEntityFieldNames(), entityField.getName())) {

					return null;
				}

				return entityField;
			});
	}

	protected String getFilterString(
		EntityField entityField, String operator, Employee employee) {

		StringBundler sb = new StringBundler();

		String entityFieldName = entityField.getName();

		sb.append(entityFieldName);

		sb.append(" ");
		sb.append(operator);
		sb.append(" ");

		if (entityFieldName.equals("companyId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("department")) {
			Object object = employee.getDepartment();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("employeeId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("firstName")) {
			Object object = employee.getFirstName();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("groupId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("isManager")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("lastName")) {
			Object object = employee.getLastName();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("level")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("managerIdPK")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("position")) {
			Object object = employee.getPosition();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("stateCode")) {
			Object object = employee.getStateCode();

			String value = String.valueOf(object);

			if (operator.equals("contains")) {
				sb = new StringBundler();

				sb.append("contains(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 2)) {
					sb.append(value.substring(1, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else if (operator.equals("startswith")) {
				sb = new StringBundler();

				sb.append("startswith(");
				sb.append(entityFieldName);
				sb.append(",'");

				if ((object != null) && (value.length() > 1)) {
					sb.append(value.substring(0, value.length() - 1));
				}
				else {
					sb.append(value);
				}

				sb.append("')");
			}
			else {
				sb.append("'");
				sb.append(value);
				sb.append("'");
			}

			return sb.toString();
		}

		if (entityFieldName.equals("status")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		if (entityFieldName.equals("userId")) {
			throw new IllegalArgumentException(
				"Invalid entity field " + entityFieldName);
		}

		throw new IllegalArgumentException(
			"Invalid entity field " + entityFieldName);
	}

	protected String invoke(String query) throws Exception {
		HttpInvoker httpInvoker = HttpInvoker.newHttpInvoker();

		httpInvoker.body(
			JSONUtil.put(
				"query", query
			).toString(),
			"application/json");
		httpInvoker.httpMethod(HttpInvoker.HttpMethod.POST);
		httpInvoker.path("http://localhost:8080/o/graphql");
		httpInvoker.userNameAndPassword("test@liferay.com:test");

		HttpInvoker.HttpResponse httpResponse = httpInvoker.invoke();

		return httpResponse.getContent();
	}

	protected JSONObject invokeGraphQLMutation(GraphQLField graphQLField)
		throws Exception {

		GraphQLField mutationGraphQLField = new GraphQLField(
			"mutation", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(mutationGraphQLField.toString()));
	}

	protected JSONObject invokeGraphQLQuery(GraphQLField graphQLField)
		throws Exception {

		GraphQLField queryGraphQLField = new GraphQLField(
			"query", graphQLField);

		return JSONFactoryUtil.createJSONObject(
			invoke(queryGraphQLField.toString()));
	}

	protected Employee randomEmployee() throws Exception {
		return new Employee() {
			{
				companyId = RandomTestUtil.randomLong();
				department = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				employeeId = RandomTestUtil.randomLong();
				firstName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				groupId = RandomTestUtil.randomLong();
				isManager = RandomTestUtil.randomBoolean();
				lastName = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				level = RandomTestUtil.randomLong();
				managerIdPK = RandomTestUtil.randomLong();
				position = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				stateCode = StringUtil.toLowerCase(
					RandomTestUtil.randomString());
				status = RandomTestUtil.randomLong();
				userId = RandomTestUtil.randomLong();
			}
		};
	}

	protected Employee randomIrrelevantEmployee() throws Exception {
		Employee randomIrrelevantEmployee = randomEmployee();

		return randomIrrelevantEmployee;
	}

	protected Employee randomPatchEmployee() throws Exception {
		return randomEmployee();
	}

	protected EmployeeResource employeeResource;
	protected com.liferay.portal.kernel.model.Group irrelevantGroup;
	protected com.liferay.portal.kernel.model.Company testCompany;
	protected com.liferay.portal.kernel.model.Group testGroup;

	protected static class BeanTestUtil {

		public static void copyProperties(Object source, Object target)
			throws Exception {

			Class<?> sourceClass = _getSuperClass(source.getClass());

			Class<?> targetClass = target.getClass();

			for (java.lang.reflect.Field field :
					sourceClass.getDeclaredFields()) {

				if (field.isSynthetic()) {
					continue;
				}

				Method getMethod = _getMethod(
					sourceClass, field.getName(), "get");

				Method setMethod = _getMethod(
					targetClass, field.getName(), "set",
					getMethod.getReturnType());

				setMethod.invoke(target, getMethod.invoke(source));
			}
		}

		public static boolean hasProperty(Object bean, String name) {
			Method setMethod = _getMethod(
				bean.getClass(), "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod != null) {
				return true;
			}

			return false;
		}

		public static void setProperty(Object bean, String name, Object value)
			throws Exception {

			Class<?> clazz = bean.getClass();

			Method setMethod = _getMethod(
				clazz, "set" + StringUtil.upperCaseFirstLetter(name));

			if (setMethod == null) {
				throw new NoSuchMethodException();
			}

			Class<?>[] parameterTypes = setMethod.getParameterTypes();

			setMethod.invoke(bean, _translateValue(parameterTypes[0], value));
		}

		private static Method _getMethod(Class<?> clazz, String name) {
			for (Method method : clazz.getMethods()) {
				if (name.equals(method.getName()) &&
					(method.getParameterCount() == 1) &&
					_parameterTypes.contains(method.getParameterTypes()[0])) {

					return method;
				}
			}

			return null;
		}

		private static Method _getMethod(
				Class<?> clazz, String fieldName, String prefix,
				Class<?>... parameterTypes)
			throws Exception {

			return clazz.getMethod(
				prefix + StringUtil.upperCaseFirstLetter(fieldName),
				parameterTypes);
		}

		private static Class<?> _getSuperClass(Class<?> clazz) {
			Class<?> superClass = clazz.getSuperclass();

			if ((superClass == null) || (superClass == Object.class)) {
				return clazz;
			}

			return superClass;
		}

		private static Object _translateValue(
			Class<?> parameterType, Object value) {

			if ((value instanceof Integer) &&
				parameterType.equals(Long.class)) {

				Integer intValue = (Integer)value;

				return intValue.longValue();
			}

			return value;
		}

		private static final Set<Class<?>> _parameterTypes = new HashSet<>(
			Arrays.asList(
				Boolean.class, Date.class, Double.class, Integer.class,
				Long.class, Map.class, String.class));

	}

	protected class GraphQLField {

		public GraphQLField(String key, GraphQLField... graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(String key, List<GraphQLField> graphQLFields) {
			this(key, new HashMap<>(), graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			GraphQLField... graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = Arrays.asList(graphQLFields);
		}

		public GraphQLField(
			String key, Map<String, Object> parameterMap,
			List<GraphQLField> graphQLFields) {

			_key = key;
			_parameterMap = parameterMap;
			_graphQLFields = graphQLFields;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder(_key);

			if (!_parameterMap.isEmpty()) {
				sb.append("(");

				for (Map.Entry<String, Object> entry :
						_parameterMap.entrySet()) {

					sb.append(entry.getKey());
					sb.append(": ");
					sb.append(entry.getValue());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append(")");
			}

			if (!_graphQLFields.isEmpty()) {
				sb.append("{");

				for (GraphQLField graphQLField : _graphQLFields) {
					sb.append(graphQLField.toString());
					sb.append(", ");
				}

				sb.setLength(sb.length() - 2);

				sb.append("}");
			}

			return sb.toString();
		}

		private final List<GraphQLField> _graphQLFields;
		private final String _key;
		private final Map<String, Object> _parameterMap;

	}

	private static final com.liferay.portal.kernel.log.Log _log =
		LogFactoryUtil.getLog(BaseEmployeeResourceTestCase.class);

	private static DateFormat _dateFormat;

	@Inject
	private management.resource.v1_0.EmployeeResource _employeeResource;

}