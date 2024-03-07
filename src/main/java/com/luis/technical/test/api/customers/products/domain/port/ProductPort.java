package com.luis.technical.test.api.customers.products.domain.port;

import com.luis.technical.test.api.customers.products.domain.model.Product;

import java.util.Optional;

public interface ProductPort {
    Optional<Product> findById(Long id);
    Product save(Product product);
    void deleteById(Long id);
    Product update(Long id, Product product);
}
