package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.CustomerUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.CustomerSpringJpaAdapter;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
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

        if (!domain.isOlder())
            throw new CustomerException(CustomerConstant.ADULT_VALIDATION_ERROR);

        if (!domain.isNameCorrect())
            throw new CustomerException(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR);

        if (!domain.isValidEmail())
            throw new CustomerException(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR);

        domain.setCreatedAt(LocalDateTime.now());
        Customer customerResponse = customerSpringJpaAdapter.save(domain);
        return customerDtoMapper.toDto(customerResponse);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> customerResponse = customerSpringJpaAdapter.findById(id);
        if (customerResponse.isEmpty())
            throw new CustomerException(CustomerConstant.CUSTOMER_NOT_FOUND);

        customerSpringJpaAdapter.deleteById(id);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerResponse = customerSpringJpaAdapter.findById(id);
        if (customerResponse.isEmpty())
            throw new CustomerException(CustomerConstant.CUSTOMER_NOT_FOUND);

        Customer customerUpdate = customerDtoMapper.toDomain(customerRequest);

        if (!customerUpdate.isOlder())
            throw new CustomerException(CustomerConstant.ADULT_VALIDATION_ERROR);

        if (!customerUpdate.isNameCorrect())
            throw new CustomerException(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR);

        if (!customerUpdate.isValidEmail())
            throw new CustomerException(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR);

        customerUpdate.setCreatedAt(customerResponse.get().getCreatedAt());
        customerUpdate.setUpdatedAt(LocalDateTime.now());

        Customer customer = customerSpringJpaAdapter.update(id, customerUpdate);
        return customerDtoMapper.toDto(customer);
    }

    @Override
    public List<CustomerResponse> findByAll() {
        return customerSpringJpaAdapter.findAll().stream().map(customerDtoMapper::toDto).toList();
    }
}
