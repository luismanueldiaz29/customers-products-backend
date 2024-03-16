package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.CustomerUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.domain.port.CustomerPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements CustomerUseCase {
    private final CustomerPort customerPort;

    public CustomerService(
            CustomerPort customerPort
    ) {
        this.customerPort = customerPort;
    }

    @Override
    public Optional<CustomerResponse> findById(Long id) {
        return customerPort.findById(id).map(CustomerDtoMapper.mapper::toDto);
    }

    @Override
    public CustomerResponse save(CustomerRequest customerRequest) {
        Customer domain = CustomerDtoMapper.mapper.toDomain(customerRequest);

        if (domain.isNotOlder())
            throw new CustomerException(CustomerConstant.ADULT_VALIDATION_ERROR);

        if (domain.isNotCorrectName())
            throw new CustomerException(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR);

        if (domain.isNotValidEmail())
            throw new CustomerException(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR);

        domain.setCreatedAt(LocalDateTime.now());
        Customer customerResponse = customerPort.save(domain);
        return CustomerDtoMapper.mapper.toDto(customerResponse);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> customerResponse = customerPort.findById(id);
        if (customerResponse.isEmpty())
            throw new CustomerException(CustomerConstant.CUSTOMER_NOT_FOUND);

        customerPort.deleteById(id);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customerResponse = customerPort.findById(id);
        if (customerResponse.isEmpty())
            throw new CustomerException(CustomerConstant.CUSTOMER_NOT_FOUND);

        Customer customerUpdate = CustomerDtoMapper.mapper.toDomain(customerRequest);

        if (customerUpdate.isNotOlder())
            throw new CustomerException(CustomerConstant.ADULT_VALIDATION_ERROR);

        if (customerUpdate.isNotCorrectName())
            throw new CustomerException(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR);

        if (customerUpdate.isNotValidEmail())
            throw new CustomerException(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR);

        customerUpdate.setCreatedAt(customerResponse.get().getCreatedAt());
        customerUpdate.setUpdatedAt(LocalDateTime.now());

        Customer customer = customerPort.update(id, customerUpdate);
        return CustomerDtoMapper.mapper.toDto(customer);
    }

    @Override
    public List<CustomerResponse> findByAll() {
        return customerPort.findAll().stream().map(CustomerDtoMapper.mapper::toDto).toList();
    }
}
