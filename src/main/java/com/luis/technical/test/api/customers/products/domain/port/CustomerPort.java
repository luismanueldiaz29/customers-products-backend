package com.luis.technical.test.api.customers.products.domain.port;

import com.luis.technical.test.api.customers.products.domain.model.Customer;

import java.util.Optional;

public interface CustomerPort {
    Optional<Customer> findById(Long id);
    Customer save(Customer customer);
    void deleteById(Long id);
    Customer update(Long id, Customer customer);
}
