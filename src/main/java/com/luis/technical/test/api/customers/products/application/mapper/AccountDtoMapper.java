package com.luis.technical.test.api.customers.products.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class AccountDtoMapper {
    private final ObjectMapper objectMapper;

    public AccountDtoMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AccountResponse toDto(Account entity){
        return objectMapper.convertValue(entity, AccountResponse.class);
    }

    public Account toDomain(AccountRequest domain){
        Account product = objectMapper.convertValue(domain, Account.class);
        if (domain.getCustomerId() != null)
            product.setCustomer(Customer.builder().id(domain.getCustomerId()).build());
        return product;
    }
}
