package com.luis.technical.test.api.customers.products.application.usecase;


import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;

public interface TransactionUseCase {
    boolean sendTransaction(TransactionRequest transactionRequest);
}
