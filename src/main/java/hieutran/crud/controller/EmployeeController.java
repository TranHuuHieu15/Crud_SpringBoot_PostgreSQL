package hieutran.crud.controller;

import hieutran.crud.config.Translator;
import hieutran.crud.dto.EmployeeDto;
import hieutran.crud.dto.response.ResponseSuccess;
import hieutran.crud.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //! Tạo mới một employee
    @PostMapping
    public ResponseSuccess<EmployeeDto> createEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto saveEmployee = employeeService.createEntity(employeeDto);
        return new ResponseSuccess<>(HttpStatus.CREATED.value(), Translator.toLocale("employee.add.success"), saveEmployee);
    }

    //! Lấy thông tin của một employee theo id
    @GetMapping("{employeeId}")
    public ResponseSuccess<EmployeeDto> getEmployeeById(@PathVariable("employeeId") Long employeeId) {
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.getOne.success"), employeeDto);
    }

    //! Lấy thông tin của tất cả employee
    @GetMapping
    public ResponseSuccess<List<EmployeeDto>> getAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.getAll.success"), employees);
    }

    //! Cập nhật thông tin của một employee
    @PutMapping("{employeeId}")
    public ResponseSuccess<String> updateEmployee(@PathVariable("employeeId") Long employeeId, @Valid @RequestBody EmployeeDto employeeDto) {
        EmployeeDto updateEmployee = employeeService.updateEmployee(employeeId, employeeDto);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.update.success"));
    }

    //! Xóa một employee
    @DeleteMapping("{employeeId}")
    public ResponseSuccess<String> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseSuccess<>(HttpStatus.OK.value(), Translator.toLocale("employee.delete.success"));
    }

}