package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDboMapper {
    private final ObjectMapper objectMapper;

    public CustomerDboMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public CustomerEntity toDbo(Customer entity){
        return objectMapper.convertValue(entity, CustomerEntity.class);
    }

    public Customer toDomain(CustomerEntity domain){
        return objectMapper.convertValue(domain, Customer.class);
    }
}
