package com.example.jdbc.service.customer;

import com.example.jdbc.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> findAll();

    Customer findById(Long id);

    void save(Customer customer);

    void deleteById(Long id);

    void update(Long id, Customer customer);
}
