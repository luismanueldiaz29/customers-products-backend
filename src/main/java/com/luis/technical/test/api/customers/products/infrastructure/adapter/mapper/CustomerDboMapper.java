package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class CustomerDboMapper {
    private final ObjectMapper objectMapper;

    public CustomerDboMapper(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public CustomerEntity toDbo(Customer entity){
        return objectMapper.convertValue(entity, CustomerEntity.class);
    }

    public Customer toDomain(CustomerEntity domain){
        return objectMapper.convertValue(domain, Customer.class);
    }
}
