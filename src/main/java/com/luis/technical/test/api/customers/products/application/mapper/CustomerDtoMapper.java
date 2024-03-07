package com.luis.technical.test.api.customers.products.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoMapper {
    private final ObjectMapper objectMapper;

    public CustomerDtoMapper(){
        this.objectMapper = new ObjectMapper();
    }

    public Customer toDomain(CustomerRequest entity){
        return objectMapper.convertValue(entity, Customer.class);
    }

    public CustomerResponse toDto(Customer domain){
        return objectMapper.convertValue(domain, CustomerResponse.class);
    }
}
