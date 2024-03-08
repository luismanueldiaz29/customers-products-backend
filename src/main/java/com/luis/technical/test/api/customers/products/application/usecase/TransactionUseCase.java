package com.luis.technical.test.api.customers.products.application.usecase;

import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;

import java.util.List;
import java.util.Optional;

public interface TransactionUseCase {
    TransactionResponse sendTransaction(TransactionRequest transactionRequest);
    List<TransactionResponse> findAll();
    Optional<TransactionResponse> findById(Long id);
}
