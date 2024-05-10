package hieutran.crud.mapper;

import hieutran.crud.dto.EmployeeDto;
import hieutran.crud.entity.Employee;

public class EmployeeMapper {
    //! Chuyển đổi từ Employee sang EmployeeDto
    public static EmployeeDto mapToEmployeeDTo(Employee employee) {
        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    //! Chuyển đổi từ EmployeeDto sang Employee
    public static Employee mapToEmployee(EmployeeDto employeeDto) {
        return new Employee(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}
