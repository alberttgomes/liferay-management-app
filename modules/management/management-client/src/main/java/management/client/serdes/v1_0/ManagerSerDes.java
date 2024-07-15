package management.client.serdes.v1_0;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.Generated;

import management.client.dto.v1_0.Manager;
import management.client.json.BaseJSONParser;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
public class ManagerSerDes {

	public static Manager toDTO(String json) {
		ManagerJSONParser managerJSONParser = new ManagerJSONParser();

		return managerJSONParser.parseToDTO(json);
	}

	public static Manager[] toDTOs(String json) {
		ManagerJSONParser managerJSONParser = new ManagerJSONParser();

		return managerJSONParser.parseToDTOs(json);
	}

	public static String toJSON(Manager manager) {
		if (manager == null) {
			return "null";
		}

		StringBuilder sb = new StringBuilder();

		sb.append("{");

		if (manager.getEmployeeIdPK() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"employeeIdPK\": ");

			sb.append(manager.getEmployeeIdPK());
		}

		if (manager.getManagerId() != null) {
			if (sb.length() > 1) {
				sb.append(", ");
			}

			sb.append("\"managerId\": ");

			sb.append(manager.getManagerId());
		}

		sb.append("}");

		return sb.toString();
	}

	public static Map<String, Object> toMap(String json) {
		ManagerJSONParser managerJSONParser = new ManagerJSONParser();

		return managerJSONParser.parseToMap(json);
	}

	public static Map<String, String> toMap(Manager manager) {
		if (manager == null) {
			return null;
		}

		Map<String, String> map = new TreeMap<>();

		if (manager.getEmployeeIdPK() == null) {
			map.put("employeeIdPK", null);
		}
		else {
			map.put("employeeIdPK", String.valueOf(manager.getEmployeeIdPK()));
		}

		if (manager.getManagerId() == null) {
			map.put("managerId", null);
		}
		else {
			map.put("managerId", String.valueOf(manager.getManagerId()));
		}

		return map;
	}

	public static class ManagerJSONParser extends BaseJSONParser<Manager> {

		@Override
		protected Manager createDTO() {
			return new Manager();
		}

		@Override
		protected Manager[] createDTOArray(int size) {
			return new Manager[size];
		}

		@Override
		protected void setField(
			Manager manager, String jsonParserFieldName,
			Object jsonParserFieldValue) {

			if (Objects.equals(jsonParserFieldName, "employeeIdPK")) {
				if (jsonParserFieldValue != null) {
					manager.setEmployeeIdPK(
						Long.valueOf((String)jsonParserFieldValue));
				}
			}
			else if (Objects.equals(jsonParserFieldName, "managerId")) {
				if (jsonParserFieldValue != null) {
					manager.setManagerId(
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