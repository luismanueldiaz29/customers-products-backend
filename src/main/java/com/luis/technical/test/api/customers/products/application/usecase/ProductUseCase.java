package com.luis.technical.test.api.customers.products.application.usecase;

import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;

import java.util.Optional;

public interface ProductUseCase {
    Optional<ProductResponse> findById(Long id);
    ProductResponse save(ProductRequest productRequest);
    void deleteById(Long id);
    ProductResponse update(Long id, ProductRequest productRequest);
}
