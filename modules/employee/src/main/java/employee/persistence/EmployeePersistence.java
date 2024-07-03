package employee.persistence;

import employee.entities.Employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public interface EmployeePersistence extends JpaRepository<Employee, Long> {

    boolean findByFirstNameAndLastName(
            String firstName, String lastName);

}
