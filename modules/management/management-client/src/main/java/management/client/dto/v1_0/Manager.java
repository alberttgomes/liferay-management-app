package management.client.dto.v1_0;

import java.io.Serializable;

import java.util.Objects;

import javax.annotation.Generated;

import management.client.function.UnsafeSupplier;
import management.client.serdes.v1_0.ManagerSerDes;

/**
 * @author Albert Cabral
 * @generated
 */
@Generated("")
public class Manager implements Cloneable, Serializable {

	public static Manager toDTO(String json) {
		return ManagerSerDes.toDTO(json);
	}

	public Long getEmployeeIdPK() {
		return employeeIdPK;
	}

	public void setEmployeeIdPK(Long employeeIdPK) {
		this.employeeIdPK = employeeIdPK;
	}

	public void setEmployeeIdPK(
		UnsafeSupplier<Long, Exception> employeeIdPKUnsafeSupplier) {

		try {
			employeeIdPK = employeeIdPKUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long employeeIdPK;

	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public void setManagerId(
		UnsafeSupplier<Long, Exception> managerIdUnsafeSupplier) {

		try {
			managerId = managerIdUnsafeSupplier.get();
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected Long managerId;

	@Override
	public Manager clone() throws CloneNotSupportedException {
		return (Manager)super.clone();
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}

		if (!(object instanceof Manager)) {
			return false;
		}

		Manager manager = (Manager)object;

		return Objects.equals(toString(), manager.toString());
	}

	@Override
	public int hashCode() {
		String string = toString();

		return string.hashCode();
	}

	public String toString() {
		return ManagerSerDes.toJSON(this);
	}

}