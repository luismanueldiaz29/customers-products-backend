package com.luis.technical.test.api.customers.products.application.usecase;

import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;

import java.util.List;
import java.util.Optional;

public interface ProductUseCase {
    Optional<AccountResponse> findById(Long id);
    AccountResponse save(AccountRequest productRequest);
    void deleteById(Long id);
    AccountResponse update(Long id, AccountRequest productRequest);
    List<AccountResponse> findByAll();
}
