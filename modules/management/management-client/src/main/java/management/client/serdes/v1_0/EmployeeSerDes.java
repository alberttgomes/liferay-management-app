package management.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import management.client.dto.v1_0.Employee;
import management.client.json.BaseJSONParser;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
public class EmployeeSerDes {

	public static Employee toDTO(String json) {
		EmployeeJSONParser employeeJSONParser = new EmployeeJSONParser();

		return employeeJSONParser.parseToDTO(json);
	}

	public static Employee[] toDTOs(String json) {
		EmployeeJSONParser employeeJSONParser = new EmployeeJSONParser();

		return employeeJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Employee employee) {
		if (employee == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (employee.getCompanyId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"companyId\": ");

			sb.append(employee.getCompanyId());
		}

		if (employee.getDepartment() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"department\": ");

			sb.append("\"");

			sb.append(_escape(employee.getDepartment()));

			sb.append("\"");
		}

		if (employee.getEmployeeId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"employeeId\": ");

			sb.append(employee.getEmployeeId());
		}

		if (employee.getFirstName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"firstName\": ");

			sb.append("\"");

			sb.append(_escape(employee.getFirstName()));

			sb.append("\"");
		}

		if (employee.getGroupId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"groupId\": ");

			sb.append(employee.getGroupId());
		}

		if (employee.getIsManager() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"isManager\": ");

			sb.append(employee.getIsManager());
		}

		if (employee.getLastName() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"lastName\": ");

			sb.append("\"");

			sb.append(_escape(employee.getLastName()));

			sb.append("\"");
		}

		if (employee.getLevel() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"level\": ");

			sb.append(employee.getLevel());
		}

		if (employee.getManagerIdPK() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"managerIdPK\": ");

			sb.append(employee.getManagerIdPK());
		}

		if (employee.getPosition() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"position\": ");

			sb.append("\"");

			sb.append(_escape(employee.getPosition()));

			sb.append("\"");
		}

		if (employee.getStateCode() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"stateCode\": ");

			sb.append("\"");

			sb.append(_escape(employee.getStateCode()));

			sb.append("\"");
		}

		if (employee.getStatus() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"status\": ");

			sb.append(employee.getStatus());
		}

		if (employee.getUserId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"userId\": ");

			sb.append(employee.getUserId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		EmployeeJSONParser employeeJSONParser = new EmployeeJSONParser();

		return employeeJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Employee employee) {
		if (employee == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (employee.getCompanyId() == null) {
			map.put("companyId", null);
		}
		else {
			map.put("companyId", String.valueOf(employee.getCompanyId()));
		}

		if (employee.getDepartment() == null) {
			map.put("department", null);
		}
		else {
			map.put("department", String.valueOf(employee.getDepartment()));
		}

		if (employee.getEmployeeId() == null) {
			map.put("employeeId", null);
		}
		else {
			map.put("employeeId", String.valueOf(employee.getEmployeeId()));
		}

		if (employee.getFirstName() == null) {
			map.put("firstName", null);
		}
		else {
			map.put("firstName", String.valueOf(employee.getFirstName()));
		}

		if (employee.getGroupId() == null) {
			map.put("groupId", null);
		}
		else {
			map.put("groupId", String.valueOf(employee.getGroupId()));
		}

		if (employee.getIsManager() == null) {
			map.put("isManager", null);
		}
		else {
			map.put("isManager", String.valueOf(employee.getIsManager()));
		}

		if (employee.getLastName() == null) {
			map.put("lastName", null);
		}
		else {
			map.put("lastName", String.valueOf(employee.getLastName()));
		}

		if (employee.getLevel() == null) {
			map.put("level", null);
		}
		else {
			map.put("level", String.valueOf(employee.getLevel()));
		}

		if (employee.getManagerIdPK() == null) {
			map.put("managerIdPK", null);
		}
		else {
			map.put("managerIdPK", String.valueOf(employee.getManagerIdPK()));
		}

		if (employee.getPosition() == null) {
			map.put("position", null);
		}
		else {
			map.put("position", String.valueOf(employee.getPosition()));
		}

		if (employee.getStateCode() == null) {
			map.put("stateCode", null);
		}
		else {
			map.put("stateCode", String.valueOf(employee.getStateCode()));
		}

		if (employee.getStatus() == null) {
			map.put("status", null);
		}
		else {
			map.put("status", String.valueOf(employee.getStatus()));
		}

		if (employee.getUserId() == null) {
			map.put("userId", null);
		}
		else {
			map.put("userId", String.valueOf(employee.getUserId()));
		}

		return map;
	}

	public static class EmployeeJSONParser extends BaseJSONParser<Employee> {

		@Override
		protected Employee createDTO() {
			return new Employee();
		}

		@Override
		protected Employee[] createDTOArray(int size) {
			return new Employee[size];
		}

		@Override
		protected void setField(
			Employee employee, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "companyId")) {
				if (jsonParserFieldValue != null) {
					employee.setCompanyId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "department")) {
				if (jsonParserFieldValue != null) {
					employee.setDepartment((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "employeeId")) {
				if (jsonParserFieldValue != null) {
					employee.setEmployeeId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "firstName")) {
				if (jsonParserFieldValue != null) {
					employee.setFirstName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "groupId")) {
				if (jsonParserFieldValue != null) {
					employee.setGroupId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "isManager")) {
				if (jsonParserFieldValue != null) {
					employee.setIsManager((Boolean)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "lastName")) {
				if (jsonParserFieldValue != null) {
					employee.setLastName((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "level")) {
				if (jsonParserFieldValue != null) {
					employee.setLevel(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "managerIdPK")) {
				if (jsonParserFieldValue != null) {
					employee.setManagerIdPK(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "position")) {
				if (jsonParserFieldValue != null) {
					employee.setPosition((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "stateCode")) {
				if (jsonParserFieldValue != null) {
					employee.setStateCode((String)jsonParserFieldValue);
				}
			}
			else if (Objects.equals(jsonParserFieldName, "status")) {
				if (jsonParserFieldValue != null) {
					employee.setStatus(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "userId")) {
				if (jsonParserFieldValue != null) {
					employee.setUserId(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
		}

	}

	private static String _escape(Object object) {
		String string = String.valueOf(object);

		for (String[] strings : BaseJSONParser.JSON_ESCAPE_STRINGS) {
			string = string.replace(strings[0], strings[1]);
		}

		return string;
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
			sb.append(entry.getKey());
			sb.append("\": ");

			Object value = entry.getValue();

			Class<?> valueClass = value.getClass();

			if (value instanceof Map) {
				sb.append(_toJSON((Map)value));
			}
			else if (valueClass.isArray()) {
				Object[] values = (Object[])value;

				sb.append("[");

				for (int i = 0; i < values.length; i++) {
					sb.append("\"");
					sb.append(_escape(values[i]));
					sb.append("\"");

					if ((i + 1) < values.length) {
						sb.append(", ");
					}
				}

				sb.append("]");
			}
			else if (value instanceof String) {
				sb.append("\"");
				sb.append(_escape(entry.getValue()));
				sb.append("\"");
			}
			else {
				sb.append(String.valueOf(entry.getValue()));
			}

			if (iterator.hasNext()) {
				sb.append(", ");
			}
		}

		sb.append("}");

		return sb.toString();
	}

}