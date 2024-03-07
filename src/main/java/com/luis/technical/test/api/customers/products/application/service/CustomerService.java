package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.CustomerUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.CustomerSpringJpaAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerUseCase {
    private final CustomerSpringJpaAdapter customerSpringJpaAdapter;
    private final CustomerDtoMapper customerDtoMapper;

    public CustomerService(
            CustomerSpringJpaAdapter customerSpringJpaAdapter,
            CustomerDtoMapper customerDtoMapper
    ) {
        this.customerSpringJpaAdapter = customerSpringJpaAdapter;
        this.customerDtoMapper = customerDtoMapper;
    }

    @Override
    public Optional<CustomerResponse> findById(Long id) {
        return customerSpringJpaAdapter.findById(id).map(customerDtoMapper::toDto);
    }

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        Customer domain = customerDtoMapper.toDomain(customerRequest);
        domain.setCreatedAt(LocalDateTime.now());
        Customer customerResponse = customerSpringJpaAdapter.save(domain);
        return customerDtoMapper.toDto(customerResponse);
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerResponse = customerSpringJpaAdapter.findById(id);

        Customer customerNew = customerDtoMapper.toDomain(customerRequest);
        customerNew.setCreatedAt(customerResponse.get().getCreatedAt());
        customerNew.setUpdatedAt(LocalDateTime.now());

        Customer customer = customerSpringJpaAdapter.update(id, customerNew);
        return customerDtoMapper.toDto(customer);
    }

    @Override
    public List<CustomerResponse> findByAll() {
        return customerSpringJpaAdapter.findAll().stream().map(customerDtoMapper::toDto).toList();
    }
}
