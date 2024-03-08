package com.luis.technical.test.api.customers.products.domain.port;

import com.luis.technical.test.api.customers.products.domain.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionPort {
    Transaction save(Transaction transaction);
    List<Transaction> findAll();
    Optional<Transaction> findById(Long id);
}
