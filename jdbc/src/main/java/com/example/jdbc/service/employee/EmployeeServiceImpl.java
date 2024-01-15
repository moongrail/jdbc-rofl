package com.example.jdbc.service.employee;

import com.example.jdbc.dto.employee.EmployeeRequest;
import com.example.jdbc.mapper.EmployeeMapper;
import com.example.jdbc.model.Employee;
import com.example.jdbc.repository.employee.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    /**
     * Я ничего не успел, понятное дело что я всё заменю потом на нормальные эксепшены и проверки. и буду возвращать
     * для того что бы сделать дто
     */
    private final EmployeeRepository employeeRepository;

    @Override
    public void save(EmployeeRequest employeeRequest) {
        Employee employeeFromRequest = EmployeeMapper.toEmployeeFromRequest(employeeRequest);
        employeeRepository.save(employeeFromRequest);
    }

    @Override
    public void update(Long id, EmployeeRequest employeeRequest) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            if (nonNull(employeeRequest.getName())) {
                Employee employeeFromRequest = EmployeeMapper.toEmployeeFromRequest(employeeRequest);
                employeeFromRequest.setId(id);
                employeeRepository.update(employeeFromRequest);
            }
        }
        System.out.println("NETY");
    }

    @Override
    public void deleteById(Long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public Optional<Employee> findById(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if (employee.isPresent()) {
            return employee;
        }
        return Optional.empty();
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
}
