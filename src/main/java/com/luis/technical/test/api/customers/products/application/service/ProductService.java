package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.ProductDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.ProductUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.ProductSpringJpaAdapter;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ProductService implements ProductUseCase {
    private final ProductSpringJpaAdapter productSpringJpaAdapter;
    private final ProductDtoMapper productDtoMapper;

    public ProductService(
            ProductSpringJpaAdapter productSpringJpaAdapter,
            ProductDtoMapper productDtoMapper
    ) {
        this.productSpringJpaAdapter = productSpringJpaAdapter;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public Optional<ProductResponse> findById(Long id) {
        return productSpringJpaAdapter.findById(id).map(productDtoMapper::toDto);
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product productNew = productDtoMapper.toDomain(productRequest);
        productNew.setCreatedAt(LocalDateTime.now());
        Product product = productSpringJpaAdapter.save(productNew);
        return productDtoMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> product = productSpringJpaAdapter.findById(id);

        productSpringJpaAdapter.deleteById(id);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Optional<Product> productResponse = productSpringJpaAdapter.findById(id);

        Product productNew = productDtoMapper.toDomain(productRequest);
        productNew.setCreatedAt(productResponse.get().getCreatedAt());
        productNew.setUpdatedAt(LocalDateTime.now());

        Product product = productSpringJpaAdapter.update(id, productNew);
        return productDtoMapper.toDto(product);
    }
}
