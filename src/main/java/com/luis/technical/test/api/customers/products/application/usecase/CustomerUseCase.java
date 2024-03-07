package com.luis.technical.test.api.customers.products.application.usecase;

import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;

import java.util.List;
import java.util.Optional;

public interface CustomerUseCase {
    Optional<CustomerResponse> findById(Long id);
    CustomerResponse save(CustomerRequest customerRequest);
    void deleteById(Long id);
    CustomerResponse update(Long id, CustomerRequest customerRequest);
    List<CustomerResponse> findByAll();
}
