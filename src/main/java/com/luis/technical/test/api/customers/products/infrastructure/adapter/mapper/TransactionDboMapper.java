package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.TransactionEntity;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class TransactionDboMapper {
    private final ObjectMapper objectMapper;

    public TransactionDboMapper(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public TransactionEntity toDbo(Transaction domain){
        return objectMapper.convertValue(domain, TransactionEntity.class);
    }

    public Transaction toDomain(TransactionEntity entity){
        return objectMapper.convertValue(entity, Transaction.class);
    }
}
