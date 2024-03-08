package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.ProductDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.ProductUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.constant.ProductConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import com.luis.technical.test.api.customers.products.domain.port.ProductPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.ProductException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService implements ProductUseCase {
    private final ProductPort productPort;
    private final ProductDtoMapper productDtoMapper;

    public ProductService(
            ProductPort productPort,
            ProductDtoMapper productDtoMapper
    ) {
        this.productPort = productPort;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public Optional<ProductResponse> findById(Long id) {
        return productPort.findById(id).map(productDtoMapper::toDto);
    }

    @Override
    public ProductResponse save(ProductRequest productRequest) {
        Product productNew = productDtoMapper.toDomain(productRequest);

        if (!productNew.balanceIsValid())
            throw new ProductException("La cuenta no puede tener un valor menos a 0");

        productNew.setCreatedAt(LocalDateTime.now());
        productNew.setAccountNumber(productNew.generateAccountNumber());
        productNew.setStatus(StatusType.ACTIVE.toString());
        Product product = productPort.save(productNew);
        return productDtoMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> productResponse = productPort.findById(id);
        if (productResponse.isEmpty())
            throw new CustomerException(ProductConstant.PRODUCT_NOT_FOUND);

        productPort.deleteById(id);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest productRequest) {
        Optional<Product> productResponse = productPort.findById(id);
        if (productResponse.isEmpty())
            throw new CustomerException(ProductConstant.PRODUCT_NOT_FOUND);

        Product productUpdate = productDtoMapper.toDomain(productRequest);
        if (!productUpdate.balanceIsValid())
            throw new ProductException(ProductConstant.BALANCE_IS_NOT_VALID);
        if (productUpdate.getStatus().equals(StatusType.INACTIVE.toString()) && !productResponse.get().canInactivateAccount())
            throw new CustomerException(ProductConstant.ACCOUNT_NOT_INACTIVE_BALANCE_IS_NOT_ZERO);

        productUpdate.setAccountNumber(productResponse.get().getAccountNumber());
        productUpdate.setCreatedAt(productResponse.get().getCreatedAt());
        productUpdate.setUpdatedAt(LocalDateTime.now());

        Product product = productPort.update(id, productUpdate);
        return productDtoMapper.toDto(product);
    }

    @Override
    public List<ProductResponse> findByAll() {
        return productPort.findAll().stream().map(productDtoMapper::toDto).toList();
    }
}
