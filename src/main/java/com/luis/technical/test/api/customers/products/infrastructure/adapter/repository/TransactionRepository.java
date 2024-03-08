package com.luis.technical.test.api.customers.products.infrastructure.adapter.repository;

import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.TransactionEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    List<TransactionEntity> findAll();
}
