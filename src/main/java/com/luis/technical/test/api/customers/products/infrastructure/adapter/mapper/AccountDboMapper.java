package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountDboMapper {
    private final ObjectMapper objectMapper;

    public AccountDboMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public AccountEntity toDbo(Account domain){
        return objectMapper.convertValue(domain, AccountEntity.class);
    }

    public Account toDomain(AccountEntity domain){
        return objectMapper.convertValue(domain, Account.class);
    }
}
