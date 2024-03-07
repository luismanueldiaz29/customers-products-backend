package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.ProductEntity;
import org.springframework.stereotype.Component;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

@Component
public class ProductDboMapper {
    private final ObjectMapper objectMapper;

    public ProductDboMapper(){
        this.objectMapper = new ObjectMapper();
        this.objectMapper.registerModule(new JavaTimeModule());
        this.objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public ProductEntity toDbo(Product entity){
        return objectMapper.convertValue(entity, ProductEntity.class);
    }

    public Product toDomain(ProductEntity domain){
        return objectMapper.convertValue(domain, Product.class);
    }
}
