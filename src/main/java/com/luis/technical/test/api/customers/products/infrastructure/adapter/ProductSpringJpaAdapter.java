package com.luis.technical.test.api.customers.products.infrastructure.adapter;

import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.port.ProductPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.ProductEntity;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper.ProductDboMapper;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductSpringJpaAdapter implements ProductPort {
    private final ProductRepository productRepository;
    private final ProductDboMapper productDboMapper;
    public ProductSpringJpaAdapter(
            ProductRepository productRepository,
            ProductDboMapper productDboMapper
    ) {
        this.productRepository = productRepository;
        this.productDboMapper = productDboMapper;
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id).map(productDboMapper::toDomain);
    }

    @Override
    public Product save(Product product) {
        ProductEntity productNew = productDboMapper.toDbo(product);
        ProductEntity productEntity = productRepository.save(productNew);
        return productDboMapper.toDomain(productEntity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Product update(Long id, Product product) {
        ProductEntity productNew = productDboMapper.toDbo(product);
        ProductEntity productEntity = productRepository.save(productNew);
        return productDboMapper.toDomain(productEntity);
    }
}
