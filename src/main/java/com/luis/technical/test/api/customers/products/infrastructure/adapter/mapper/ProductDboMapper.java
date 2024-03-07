package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.ProductEntity;
import org.springframework.stereotype.Component;

@Component
public class ProductDboMapper {
    private final ObjectMapper objectMapper;

    public ProductDboMapper(){
        this.objectMapper = new ObjectMapper();
    }

    public ProductEntity toDbo(Product entity){
        return objectMapper.convertValue(entity, ProductEntity.class);
    }

    public Product toDomain(ProductEntity domain){
        return objectMapper.convertValue(domain, Product.class);
    }
}
