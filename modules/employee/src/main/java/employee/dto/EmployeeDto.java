package employee.dto;

import javax.validation.constraints.NotBlank;

public class EmployeeDto {

    @NotBlank
    public String fistName;

    @NotBlank
    public String lastName;

    @NotBlank
    public String position;

    @NotBlank
    public String stateCode;

}
