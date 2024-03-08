package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.AccountEntity;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class AccountDboMapper {
    private final ObjectMapper objectMapper;

    public AccountDboMapper(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public AccountEntity toDbo(Account domain){
        return objectMapper.convertValue(domain, AccountEntity.class);
    }

    public Account toDomain(AccountEntity domain){
        return objectMapper.convertValue(domain, Account.class);
    }
}
