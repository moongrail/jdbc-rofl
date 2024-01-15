package com.example.jdbc.service.customer;

import com.example.jdbc.model.Customer;
import com.example.jdbc.repository.customer.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        return customer;
    }

    @Override
    public void update(Long id, Customer project) {
        customerRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found"));

        if (Objects.nonNull(project.getName())) {
            customerRepository.update(project);
        }
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }
}
