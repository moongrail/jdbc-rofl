package com.example.jdbc.mapper;

import com.example.jdbc.dto.employee.EmployeeRequest;
import com.example.jdbc.dto.employee.EmployeeResponse;
import com.example.jdbc.model.Employee;

import java.util.List;
import java.util.stream.Collectors;

public final class EmployeeMapper {
    public static EmployeeResponse toDto(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .build();
    }

    public static Employee toEmployeeFromRequest(EmployeeRequest employeeRequest) {
        return Employee.builder()
                .name(employeeRequest.getName())
                .build();
    }

    public static List<EmployeeResponse> toDtoList(List<Employee> employees) {
        return employees.stream()
                .map(EmployeeMapper::toDto)
                .collect(Collectors.toList());
    }
}
