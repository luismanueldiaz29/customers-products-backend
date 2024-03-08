package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.TransactionMapper;
import com.luis.technical.test.api.customers.products.application.usecase.TransactionUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.constant.TransactionConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import com.luis.technical.test.api.customers.products.domain.port.ProductPort;
import com.luis.technical.test.api.customers.products.domain.port.TransactionPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class TransactionService implements TransactionUseCase {
    private final TransactionPort transactionPort;
    private final TransactionMapper transactionMapper;
    private final ProductPort productPort;

    public TransactionService(
            TransactionPort transactionPort,
            TransactionMapper transactionMapper,
            ProductPort productPort
    ) {
        this.transactionPort = transactionPort;
        this.transactionMapper = transactionMapper;
        this.productPort = productPort;
    }


    @Override
    @Transactional
    public TransactionResponse sendTransaction(TransactionRequest transactionRequest) {
        Optional<Product> sourceAccount = productPort.findByAccountNumber(transactionRequest.getSourceAccountNumber());
        if (sourceAccount.isEmpty())
            throw new TransactionException(TransactionConstant.ACCOUNT_SOURCE_NOT_FOUND);

        Optional<Product> destinationAccount = Optional.empty();
        if (transactionRequest.getType().equals(TransactionType.WITHDRAWAL)) {
            if (!sourceAccount.get().isBalanceGreaterThan(transactionRequest.getAmount()))
                throw new TransactionException(TransactionConstant.ACCOUNT_NOT_HAVE_ENOUGH_BALANCE);
            sourceAccount.get().subtract(transactionRequest.getAmount());
        } else {
            destinationAccount = productPort.findByAccountNumber(transactionRequest.getDestinationAccountNumber());
            if (destinationAccount.isEmpty())
                throw new TransactionException(TransactionConstant.ACCOUNT_DESTINATION_NOT_FOUND);
            sourceAccount.get().subtract(transactionRequest.getAmount());
            destinationAccount.get().plus(transactionRequest.getAmount());
            productPort.update(destinationAccount.get().getId(), destinationAccount.get());
        }

        productPort.update(sourceAccount.get().getId(), sourceAccount.get());
        Transaction transaction = getTransaction(transactionRequest, destinationAccount, sourceAccount.get());
        Transaction savedTransaction = transactionPort.save(transaction);
        return transactionMapper.toDto(savedTransaction);
    }

    private Transaction getTransaction(TransactionRequest transactionRequest, Optional<Product> destinationAccount, Product sourceAccount) {
        return Transaction.builder()
                .amount(transactionRequest.getAmount())
                .destinationAccount(destinationAccount.orElse(null))
                .sourceAccount(sourceAccount)
                .type(transactionRequest.getType())
                .build();
    }
}
