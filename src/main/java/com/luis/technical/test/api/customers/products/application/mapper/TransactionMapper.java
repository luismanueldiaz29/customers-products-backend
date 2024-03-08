package com.luis.technical.test.api.customers.products.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class TransactionMapper {
    private final ObjectMapper objectMapper;

    public TransactionMapper(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public TransactionResponse toDto(Transaction entity){
        return objectMapper.convertValue(entity, TransactionResponse.class);
    }

    public Transaction toDomain(TransactionRequest domain){
        return objectMapper.convertValue(domain, Transaction.class);
    }
}
