package com.example.jdbc.controller.employee;

import com.example.jdbc.dto.employee.EmployeeRequest;
import com.example.jdbc.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "Employee API")
@RequiredArgsConstructor
@RequestMapping("/employee")
@RestController
@Validated
public class EmployeeRestController {
    private final EmployeeService employeeService;

    @ApiOperation("Удалить рабочего по айди")
    @PostMapping(value = "/delete/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable("id") @NotNull @Positive Long id) {
        employeeService.deleteById(id);
    }

    @ApiOperation("Обновить рабочего по айди")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") Long id,
                       @RequestBody @Valid EmployeeRequest employeeRequest) {
        employeeService.update(id, employeeRequest);
    }
}
