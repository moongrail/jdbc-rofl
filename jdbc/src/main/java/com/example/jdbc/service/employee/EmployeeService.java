package com.example.jdbc.service.employee;

import com.example.jdbc.dto.employee.EmployeeRequest;
import com.example.jdbc.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    void save(EmployeeRequest employeeRequest);

    void update(Long id, EmployeeRequest employeeRequest);

    void deleteById(Long id);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();
}
