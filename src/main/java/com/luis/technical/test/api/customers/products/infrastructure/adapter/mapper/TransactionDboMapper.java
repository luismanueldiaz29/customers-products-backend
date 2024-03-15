package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionDboMapper {
    private final ObjectMapper objectMapper;

    public TransactionDboMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public TransactionEntity toDbo(Transaction domain){
        return objectMapper.convertValue(domain, TransactionEntity.class);
    }

    public Transaction toDomain(TransactionEntity entity){
        return objectMapper.convertValue(entity, Transaction.class);
    }
}
