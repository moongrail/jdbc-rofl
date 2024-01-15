package com.example.jdbc.controller.employee;

import com.example.jdbc.dto.employee.EmployeeRequest;
import com.example.jdbc.dto.employee.EmployeeResponse;
import com.example.jdbc.service.employee.EmployeeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.jdbc.mapper.EmployeeMapper.toDtoList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Api(tags = "Employee API")
@Controller
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @ApiOperation("Вывести главную страницу по рабочим")
    @GetMapping
    public String getEmployeePage(Model model) {
        model.addAttribute("employees", toDtoList(employeeService.findAll()));
        return "employees";
    }

    @ApiOperation("Вывести конкретного рабочего")
    @GetMapping("/{id}")
    public String getEmployee(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.findById(id).get());
        return "person";
    }

    @ApiOperation("Добавить рабочего")
    @PostMapping(value = "/add/json", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public List<EmployeeResponse> save(@RequestBody EmployeeRequest employeeRequest) {
        employeeService.save(employeeRequest);
        return toDtoList(employeeService.findAll());
    }
}
