package hieutran.crud.service;

import hieutran.crud.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto createEntity(EmployeeDto employeeDto);

    EmployeeDto getEmployeeById(Long employeeId);

    List<EmployeeDto> getAllEmployees();

    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);

    void deleteEmployee(Long employeeId);
}
