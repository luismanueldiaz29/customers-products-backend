package com.luis.technical.test.api.customers.products.application.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    private final ObjectMapper objectMapper;

    public ProductDtoMapper(){
        this.objectMapper = new ObjectMapper();
    }

    public ProductResponse toDto(Product entity){
        return objectMapper.convertValue(entity, ProductResponse.class);
    }

    public Product toDomain(ProductRequest domain){
        Product product = objectMapper.convertValue(domain, Product.class);
        if (domain.getCustomerId() != null)
            product.setCustomer(Customer.builder().id(domain.getCustomerId()).build());
        return product;
    }
}
