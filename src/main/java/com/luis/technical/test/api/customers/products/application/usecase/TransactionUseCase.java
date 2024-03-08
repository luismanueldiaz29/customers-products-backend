package com.luis.technical.test.api.customers.products.application.usecase;


import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;

public interface TransactionUseCase {
    TransactionResponse sendTransaction(TransactionRequest transactionRequest);
}
