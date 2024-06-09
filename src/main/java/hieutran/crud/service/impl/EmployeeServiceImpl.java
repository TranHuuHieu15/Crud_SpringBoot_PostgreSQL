package hieutran.crud.service.impl;

import hieutran.crud.dto.EmployeeDto;
import hieutran.crud.entity.Employee;
import hieutran.crud.exception.ResourceConflictException;
import hieutran.crud.exception.ResourceNotFoundException;
import hieutran.crud.mapper.EmployeeMapper;
import hieutran.crud.repository.EmployeeRepository;
import hieutran.crud.service.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j //! Để sử dụng log
@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public EmployeeDto createEntity(EmployeeDto employeeDto) {
        //! Chuyển đổi từ EmployeeDto sang Employee
        Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee existingEmployee = employeeRepository.findByEmail(employee.getEmail());
        if (existingEmployee != null) {
            throw new ResourceConflictException("Email is already taken!");
        }
        Employee saveEmployee = employeeRepository.save(employee);
        log.info("Employee {} has been created", saveEmployee.getId());
        //!Chuyển đổi từ Employee sang EmployeeDto (đã lưu vào database là employee còn trả về là employeeDto)
        return EmployeeMapper.mapToEmployeeDTo(saveEmployee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        log.info("Employee {} has been found", employee.getId());
        return EmployeeMapper.mapToEmployeeDTo(employee);
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        log.info("Retrieved {} employees", employees.size());
        return employees.stream().map(EmployeeMapper::mapToEmployeeDTo).collect(Collectors.toList());
        /*
         * collect(Collectors.toList(): Chuyển đổi Stream của các đối tượng EmployeeDto trả thành một danh sách
         */
    }

    @Override
    public Page<EmployeeDto> getAllEmployees(int page, int size, String sort) {
        int p = 0;
        if (page > 0) {
            p = page - 1;
        }
        Pageable pageable = PageRequest.of(p, size, Sort.by(sort));
        Page<Employee> employees = employeeRepository.findAll(pageable);
        return employees.map(EmployeeMapper::mapToEmployeeDTo);
    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto) {
        Employee existingEmployee = employeeRepository.findByEmail(employeeDto.getEmail());
        if (existingEmployee != null && !existingEmployee.getId().equals(employeeId)) {
            throw new ResourceConflictException("Email is already taken!");
        }
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee.setEmail(employeeDto.getEmail());
        Employee updateEmployee = employeeRepository.save(employee);
        log.info("Employee {} has been updated", updateEmployee.getId());
        return EmployeeMapper.mapToEmployeeDTo(updateEmployee);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with id: " + employeeId));
        log.info("Employee {} has been deleted", employee.getId());
        employeeRepository.deleteById(employeeId);
    }
}
