package com.example.jdbc.service;

import com.example.jdbc.repository.customer.CustomerRepository;
import com.example.jdbc.repository.employee.EmployeeRepository;
import com.example.jdbc.repository.project.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DropDataServiceImpl implements DropDataService {
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    /**
     * Можно по отдельности удалять по параметрам какие например удалить именно таблицы, но я не успеваю.
     */
    @Override
    public void drop() {
        projectRepository.deleteAll();
        employeeRepository.deleteAll();
        customerRepository.deleteAll();
    }
}
