package com.luis.technical.test.api.customers.products.infrastructure.adapter;

import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.port.TransactionPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.TransactionEntity;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper.TransactionDboMapper;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionSpringJpaAdapter implements TransactionPort {
    private final TransactionRepository transactionRepository;
    private final TransactionDboMapper transactionDboMapper;
    public TransactionSpringJpaAdapter(
            TransactionRepository transactionRepository,
            TransactionDboMapper transactionDboMapper
    ) {
        this.transactionRepository = transactionRepository;
        this.transactionDboMapper = transactionDboMapper;
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll().stream().map(transactionDboMapper::toDomain).toList();
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity transactionNew = transactionDboMapper.toDbo(transaction);
        TransactionEntity transactionEntity = transactionRepository.save(transactionNew);
        return transactionDboMapper.toDomain(transactionEntity);
    }
}
