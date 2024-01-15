package com.example.jdbc.repository.customer;

import com.example.jdbc.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository {
    void save(Customer employee);

    void update(Customer employee);

    void deleteById(Long id);

    Optional<Customer> findById(Long id);

    List<Customer> findAll();

    void deleteAll();
}
