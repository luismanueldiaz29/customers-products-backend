package com.luis.technical.test.api.customers.products.infrastructure.adapter;

import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.port.CustomerPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper.CustomerDboMapper;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerSpringJpaAdapter implements CustomerPort {
    private final CustomerRepository customerRepository;
    private final CustomerDboMapper customerDboMapper;
    public CustomerSpringJpaAdapter(
            CustomerRepository customerRepository,
            CustomerDboMapper customerDboMapper
    ) {
        this.customerRepository = customerRepository;
        this.customerDboMapper = customerDboMapper;
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id).map(customerDboMapper::toDomain);
    }

    @Override
    public Customer save(Customer customer) {
        CustomerEntity customerNew = customerDboMapper.toDbo(customer);
        CustomerEntity customerEntity = customerRepository.save(customerNew);
        return customerDboMapper.toDomain(customerEntity);
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public Customer update(Long id, Customer customer) {
        CustomerEntity customerNew = customerDboMapper.toDbo(customer);
        CustomerEntity customerEntity = customerRepository.save(customerNew);
        return customerDboMapper.toDomain(customerEntity);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll().stream().map(customerDboMapper::toDomain).toList();
    }
}
