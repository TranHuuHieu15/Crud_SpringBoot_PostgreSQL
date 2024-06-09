package hieutran.crud.service;

import hieutran.crud.dto.EmployeeDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEntity(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    Page<EmployeeDto> getAllEmployees(int page, int size, String sort);

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(Long employeeId);
}
