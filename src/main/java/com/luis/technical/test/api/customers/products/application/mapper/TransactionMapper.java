package com.luis.technical.test.api.customers.products.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
    private final ObjectMapper objectMapper;

    public TransactionMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public TransactionResponse toDto(Transaction entity){
        return objectMapper.convertValue(entity, TransactionResponse.class);
    }

    public Transaction toDomain(TransactionRequest domain){
        return objectMapper.convertValue(domain, Transaction.class);
    }
}
