package com.example.jdbc.repository.employee;

import com.example.jdbc.model.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
    void save(Employee employee);

    void update(Employee employee);

    void deleteById(Long id);

    Optional<Employee> findById(Long id);

    List<Employee> findAll();

    void deleteAll();
}
