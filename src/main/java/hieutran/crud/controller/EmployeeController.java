package hieutran.crud.controller;

import hieutran.crud.config.Translator;
import hieutran.crud.dto.EmployeeDto;
import hieutran.crud.dto.response.ResponseSuccess;
import hieutran.crud.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
@Tag(name = "Employee Controller", description = "API for Employee")
public class EmployeeController {

    private EmployeeService employeeService;

    //! Tạo mới một employee
    @Operation(summary = "Create a new employee", description = "Create a new employee")
    @PostMapping
    public ResponseSuccess<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto saveEmployee = employeeService.createEntity(employeeDto);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(), Translator.toLocale("employee.add.success"), saveEmployee);
    }

    @Operation(summary = "Get one employee by id", description = "Get one employee by id")
    //! Lấy thông tin của một employee theo id
    @GetMapping("{employeeId}")
    public ResponseSuccess<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.getOne.success"), employeeDto);
    }

    @Operation(summary = "Get all employees", description = "Get all employees")
    //! Lấy thông tin của tất cả employee
    @GetMapping
    public ResponseSuccess<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.getAll.success"), employees);
    }

    @Operation(summary = "Update an employee by id", description = "Update an employee by id")
    //! Cập nhật thông tin của một employee
    @PutMapping("{employeeId}")
    public ResponseSuccess<String> updateEmployee(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updateEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.update.success"));
    }

    @Operation(summary = "Delete an employee by id", description = "Delete an employee by id")
    //! Xóa một employee
    @DeleteMapping("{employeeId}")
    public ResponseSuccess<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.delete.success"));
    }

}