package com.example.jdbc.service;

import com.example.jdbc.model.*;
import com.example.jdbc.repository.customer.CustomerRepository;
import com.example.jdbc.repository.employee.EmployeeRepository;
import com.example.jdbc.repository.project.ProjectRepository;
import com.example.jdbc.repository.position.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FillDataServiceImpl implements FillDataService {
    public static final int MIN_NAME_LENGTH = 3;
    public static final int MAX_NAME_LENGTH = 10;

    private final ProjectRepository projectRepository;
    private final EmployeeRepository employeeRepository;
    private final CustomerRepository customerRepository;
    private final PositionRepository positionRepository;

    @Override
    @Transactional
    public void fill() {
        loadProjects();
        loadEmployees();
        loadCustomers();
        loadPositions();
    }

    private void loadProjects() {
        Random random = new Random();

        for (int i = 0; i < MAX_NAME_LENGTH; i++) {
            String projectName = generateRandomName(random, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
            Project project = new Project();
            project.setName(projectName);
            projectRepository.save(project);
        }
    }

    private void loadEmployees() {
        Random random = new Random();

        for (int i = 0; i < MAX_NAME_LENGTH; i++) {
            String employeeName = generateRandomName(random, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
            Employee employee = new Employee();
            employee.setName(employeeName);
            employeeRepository.save(employee);
        }
    }

    private void loadCustomers() {
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            String customerName = generateRandomName(random, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
            Customer customer = new Customer();
            customer.setName(customerName);
            customerRepository.save(customer);
        }
    }

    private void loadPositions() {
        List<PositionType> positionTypes = List.of(PositionType.Директор, PositionType.Разработчик,
                PositionType.Менеджер, PositionType.Потребитель);

        for (PositionType positionType : positionTypes) {
            Position position = new Position();
            position.setPositionType(positionType);
            positionRepository.save(position);
        }
    }
    //TODO: доделать связи
//    private void assignEmployeesToProjects() {
//        List<Project> projects = projectRepository.findAll();
//        List<Employee> employees = employeeRepository.findAll();
//        List<Position> positions = positionRepository.findAll();
//
//        Random random = new Random();
//
//        for (Project project : projects) {
//            for (int i = 0; i < MIN_NAME_LENGTH; i++) {
//                Employee employee = getRandomElement(employees, random);
//                Position position = getRandomElement(positions, random);
//
//                EmployeeProjectPosition employeeProjectPosition = new EmployeeProjectPosition();
//                employeeProjectPosition.setEmployee(employee);
//                employeeProjectPosition.setProject(project);
//                employeeProjectPosition.setPosition(position);
//
//                project.getEmployeeProjectPositions().add(employeeProjectPosition);
//            }
//        }
//
//        projectRepository.saveAll(projects);
//    }

    private <T> T getRandomElement(List<T> list, Random random) {
        int index = random.nextInt(list.size());
        return list.get(index);
    }

    private String generateRandomName(Random random, int minLength, int maxLength) {
        int nameLength = random.nextInt(maxLength - minLength + 1) + minLength;
        StringBuilder nameBuilder = new StringBuilder();

        for (int i = 0; i < nameLength; i++) {
            char randomChar = (char) (random.nextInt(26) + 'a');
            nameBuilder.append(randomChar);
        }

        return nameBuilder.toString();
    }
}
