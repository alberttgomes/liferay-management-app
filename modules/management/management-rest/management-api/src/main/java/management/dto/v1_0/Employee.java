package management.dto.v1_0;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liferay.petra.function.UnsafeSupplier;
import com.liferay.petra.string.StringBundler;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLField;
import com.liferay.portal.vulcan.graphql.annotation.GraphQLName;
import com.liferay.portal.vulcan.util.ObjectMapperUtil;

import io.swagger.v3.oas.annotations.media.Schema;

import java.io.Serializable;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Supplier;

import javax.annotation.Generated;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
@GraphQLName(description = "https://schema.org/Employee", value = "Employee")
@JsonFilter("Liferay.Vulcan")
@XmlRootElement(name = "Employee")
public class Employee implements Serializable {

	public static Employee toDTO(String json) {
		return ObjectMapperUtil.readValue(Employee.class, json);
	}

	public static Employee unsafeToDTO(String json) {
		return ObjectMapperUtil.unsafeReadValue(Employee.class, json);
	}

	@Schema
	public Long getCompanyId() {
		if (_companyIdSupplier != null) {
			companyId = _companyIdSupplier.get();

			_companyIdSupplier = null;
		}

		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;

		_companyIdSupplier = null;
	}

	@JsonIgnore
	public void setCompanyId(
		UnsafeSupplier<Long, Exception> companyIdUnsafeSupplier) {

		_companyIdSupplier = () -> {
			try {
				return companyIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long companyId;

	@JsonIgnore
	private Supplier<Long> _companyIdSupplier;

	@Schema
	public String getDepartment() {
		if (_departmentSupplier != null) {
			department = _departmentSupplier.get();

			_departmentSupplier = null;
		}

		return department;
	}

	public void setDepartment(String department) {
		this.department = department;

		_departmentSupplier = null;
	}

	@JsonIgnore
	public void setDepartment(
		UnsafeSupplier<String, Exception> departmentUnsafeSupplier) {

		_departmentSupplier = () -> {
			try {
				return departmentUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String department;

	@JsonIgnore
	private Supplier<String> _departmentSupplier;

	@Schema
	public Long getEmployeeId() {
		if (_employeeIdSupplier != null) {
			employeeId = _employeeIdSupplier.get();

			_employeeIdSupplier = null;
		}

		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;

		_employeeIdSupplier = null;
	}

	@JsonIgnore
	public void setEmployeeId(
		UnsafeSupplier<Long, Exception> employeeIdUnsafeSupplier) {

		_employeeIdSupplier = () -> {
			try {
				return employeeIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long employeeId;

	@JsonIgnore
	private Supplier<Long> _employeeIdSupplier;

	@Schema
	public String getFirstName() {
		if (_firstNameSupplier != null) {
			firstName = _firstNameSupplier.get();

			_firstNameSupplier = null;
		}

		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;

		_firstNameSupplier = null;
	}

	@JsonIgnore
	public void setFirstName(
		UnsafeSupplier<String, Exception> firstNameUnsafeSupplier) {

		_firstNameSupplier = () -> {
			try {
				return firstNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String firstName;

	@JsonIgnore
	private Supplier<String> _firstNameSupplier;

	@Schema
	public Long getGroupId() {
		if (_groupIdSupplier != null) {
			groupId = _groupIdSupplier.get();

			_groupIdSupplier = null;
		}

		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;

		_groupIdSupplier = null;
	}

	@JsonIgnore
	public void setGroupId(
		UnsafeSupplier<Long, Exception> groupIdUnsafeSupplier) {

		_groupIdSupplier = () -> {
			try {
				return groupIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long groupId;

	@JsonIgnore
	private Supplier<Long> _groupIdSupplier;

	@Schema
	public Boolean getIsManager() {
		if (_isManagerSupplier != null) {
			isManager = _isManagerSupplier.get();

			_isManagerSupplier = null;
		}

		return isManager;
	}

	public void setIsManager(Boolean isManager) {
		this.isManager = isManager;

		_isManagerSupplier = null;
	}

	@JsonIgnore
	public void setIsManager(
		UnsafeSupplier<Boolean, Exception> isManagerUnsafeSupplier) {

		_isManagerSupplier = () -> {
			try {
				return isManagerUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Boolean isManager;

	@JsonIgnore
	private Supplier<Boolean> _isManagerSupplier;

	@Schema
	public String getLastName() {
		if (_lastNameSupplier != null) {
			lastName = _lastNameSupplier.get();

			_lastNameSupplier = null;
		}

		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;

		_lastNameSupplier = null;
	}

	@JsonIgnore
	public void setLastName(
		UnsafeSupplier<String, Exception> lastNameUnsafeSupplier) {

		_lastNameSupplier = () -> {
			try {
				return lastNameUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String lastName;

	@JsonIgnore
	private Supplier<String> _lastNameSupplier;

	@Schema
	public Long getLevel() {
		if (_levelSupplier != null) {
			level = _levelSupplier.get();

			_levelSupplier = null;
		}

		return level;
	}

	public void setLevel(Long level) {
		this.level = level;

		_levelSupplier = null;
	}

	@JsonIgnore
	public void setLevel(UnsafeSupplier<Long, Exception> levelUnsafeSupplier) {
		_levelSupplier = () -> {
			try {
				return levelUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long level;

	@JsonIgnore
	private Supplier<Long> _levelSupplier;

	@Schema
	public Long getManagerIdFk() {
		if (_managerIdPKSupplier != null) {
			managerIdFk = _managerIdPKSupplier.get();

			_managerIdPKSupplier = null;
		}

		return managerIdFk;
	}

	public void setManagerIdFk(Long managerIdFk) {
		this.managerIdFk = managerIdFk;

		_managerIdPKSupplier = null;
	}

	@JsonIgnore
	public void setManagerIdPK(
		UnsafeSupplier<Long, Exception> managerIdPKUnsafeSupplier) {

		_managerIdPKSupplier = () -> {
			try {
				return managerIdPKUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long managerIdFk;

	@JsonIgnore
	private Supplier<Long> _managerIdPKSupplier;

	@Schema
	public String getPosition() {
		if (_positionSupplier != null) {
			position = _positionSupplier.get();

			_positionSupplier = null;
		}

		return position;
	}

	public void setPosition(String position) {
		this.position = position;

		_positionSupplier = null;
	}

	@JsonIgnore
	public void setPosition(
		UnsafeSupplier<String, Exception> positionUnsafeSupplier) {

		_positionSupplier = () -> {
			try {
				return positionUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String position;

	@JsonIgnore
	private Supplier<String> _positionSupplier;

	@Schema
	public String getStateCode() {
		if (_stateCodeSupplier != null) {
			stateCode = _stateCodeSupplier.get();

			_stateCodeSupplier = null;
		}

		return stateCode;
	}

	public void setStateCode(String stateCode) {
		this.stateCode = stateCode;

		_stateCodeSupplier = null;
	}

	@JsonIgnore
	public void setStateCode(
		UnsafeSupplier<String, Exception> stateCodeUnsafeSupplier) {

		_stateCodeSupplier = () -> {
			try {
				return stateCodeUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected String stateCode;

	@JsonIgnore
	private Supplier<String> _stateCodeSupplier;

	@Schema
	public Long getStatus() {
		if (_statusSupplier != null) {
			status = _statusSupplier.get();

			_statusSupplier = null;
		}

		return status;
	}

	public void setStatus(Long status) {
		this.status = status;

		_statusSupplier = null;
	}

	@JsonIgnore
	public void setStatus(
		UnsafeSupplier<Long, Exception> statusUnsafeSupplier) {

		_statusSupplier = () -> {
			try {
				return statusUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long status;

	@JsonIgnore
	private Supplier<Long> _statusSupplier;

	@Schema
	public Long getUserId() {
		if (_userIdSupplier != null) {
			userId = _userIdSupplier.get();

			_userIdSupplier = null;
		}

		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;

		_userIdSupplier = null;
	}

	@JsonIgnore
	public void setUserId(
		UnsafeSupplier<Long, Exception> userIdUnsafeSupplier) {

		_userIdSupplier = () -> {
			try {
				return userIdUnsafeSupplier.get();
			}
			catch (RuntimeException runtimeException) {
				throw runtimeException;
			}
			catch (Exception exception) {
				throw new RuntimeException(exception);
			}
		};
	}

	@GraphQLField
	@JsonProperty(access = JsonProperty.Access.READ_WRITE)
	protected Long userId;

	@JsonIgnore
	private Supplier<Long> _userIdSupplier;

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Employee)) {
			return false;
		}

		Employee employee = (Employee)object;

		return Objects.equals(toString(), employee.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		StringBundler sb = new StringBundler();

		sb.append("{");

		Long companyId = getCompanyId();

		if (companyId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"companyId\": ");

			sb.append(companyId);
		}

		String department = getDepartment();

		if (department != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"department\": ");

			sb.append("\"");

			sb.append(_escape(department));

			sb.append("\"");
		}

		Long employeeId = getEmployeeId();

		if (employeeId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"employeeId\": ");

			sb.append(employeeId);
		}

		String firstName = getFirstName();

		if (firstName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(firstName));

			sb.append("\"");
		}

		Long groupId = getGroupId();

		if (groupId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"groupId\": ");

			sb.append(groupId);
		}

		Boolean isManager = getIsManager();

		if (isManager != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"isManager\": ");

			sb.append(isManager);
		}

		String lastName = getLastName();

		if (lastName != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(lastName));

			sb.append("\"");
		}

		Long level = getLevel();

		if (level != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"level\": ");

			sb.append(level);
		}

		Long managerIdPK = getManagerIdFk();

		if (managerIdPK != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"managerIdPK\": ");

			sb.append(managerIdPK);
		}

		String position = getPosition();

		if (position != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"position\": ");

			sb.append("\"");

			sb.append(_escape(position));

			sb.append("\"");
		}

		String stateCode = getStateCode();

		if (stateCode != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stateCode\": ");

			sb.append("\"");

			sb.append(_escape(stateCode));

			sb.append("\"");
		}

		Long status = getStatus();

		if (status != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(status);
		}

		Long userId = getUserId();

		if (userId != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(userId);
		}

		sb.append("}");

		return sb.toString();
	}

	@Schema(
		accessMode = Schema.AccessMode.READ_ONLY,
		defaultValue = "management.dto.v1_0.Employee", name = "x-class-name"
	)
	public String xClassName;

	private static String _escape(Object object) {
		return StringUtil.replace(
			String.valueOf(object), _JSON_ESCAPE_STRINGS[0],
			_JSON_ESCAPE_STRINGS[1]);
	}

	private static boolean _isArray(Object value) {
		if (value == null) {
			return false;
		}

		Class<?> clazz = value.getClass();

		return clazz.isArray();
	}

	private static String _toJSON(Map<String, ?> map) {
		StringBuilder sb = new StringBuilder("{");

		@SuppressWarnings("unchecked")
		Set set = map.entrySet();

		@SuppressWarnings("unchecked")
		Iterator<Map.Entry<String, ?>> iterator = set.iterator();

		while (iterator.hasNext()) {
			Map.Entry<String, ?> entry = iterator.next();

			sb.append("\"");
			sb.append(_escape(entry.getKey()));
			sb.append("\": ");

			Object value = entry.getValue();

			if (_isArray(value)) {
				sb.append("[");

				Object[] valueArray = (Object[])value;

				for (int i = 0; i < valueArray.length; i++) {
					if (valueArray[i] instanceof String) {
						sb.append("\"");
						sb.append(valueArray[i]);
						sb.append("\"");
					}
					else {
						sb.append(valueArray[i]);
					}

					if ((i + 1) < valueArray.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof Map) {
				sb.append(_toJSON((Map<String, ?>)value));
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(value));
				sb.append("\"");
			}
			else {
				sb.append(value);
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

	private static final String[][] _JSON_ESCAPE_STRINGS = {
		{"\\", "\"", "\b", "\f", "\n", "\r", "\t"},
		{"\\\\", "\\\"", "\\b", "\\f", "\\n", "\\r", "\\t"}
	};

	private Map<String, Serializable> _extendedProperties;

}