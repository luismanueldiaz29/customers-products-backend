package com.luis.technical.test.api.customers.products.infrastructure.adapter.repository;

import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends CrudRepository<ProductEntity, Long> {
    List<ProductEntity> findAll();
    Optional<ProductEntity> findByAccountNumber(String accountNumber);
}
