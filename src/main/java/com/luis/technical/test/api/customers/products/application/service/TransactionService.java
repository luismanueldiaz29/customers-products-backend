package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.TransactionMapper;
import com.luis.technical.test.api.customers.products.application.usecase.TransactionUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.constant.TransactionConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.domain.port.TransactionPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.TransactionException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService implements TransactionUseCase {
    private final TransactionPort transactionPort;
    private final AccountPort productPort;

    public TransactionService(
            TransactionPort transactionPort,
            AccountPort productPort
    ) {
        this.transactionPort = transactionPort;
        this.productPort = productPort;
    }


    @Override
    @Transactional
    public TransactionResponse sendTransaction(TransactionRequest transactionRequest) {
        Optional<Account> sourceAccount = productPort.findByAccountNumber(transactionRequest.getSourceAccountNumber());
        if (sourceAccount.isEmpty())
            throw new TransactionException(TransactionConstant.ACCOUNT_SOURCE_NOT_FOUND);
        if (!sourceAccount.get().isBalanceGreaterThan(transactionRequest.getAmount()))
            throw new TransactionException(TransactionConstant.ACCOUNT_NOT_HAVE_ENOUGH_BALANCE);

        Optional<Account> destinationAccount = Optional.empty();
        if (transactionRequest.getType().equals(TransactionType.WITHDRAWAL)) {
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
        return TransactionMapper.MAPPER.toDto(savedTransaction);
    }

    @Override
    public List<TransactionResponse> findAll() {
        return transactionPort.findAll().stream().map(TransactionMapper.MAPPER::toDto).toList();
    }

    @Override
    public Optional<TransactionResponse> findById(Long id) {
        return transactionPort.findById(id).map(TransactionMapper.MAPPER::toDto);
    }

    private Transaction getTransaction(TransactionRequest transactionRequest, Optional<Account> destinationAccount, Account sourceAccount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setDestinationAccount(destinationAccount.orElse(null));
        transaction.setSourceAccount(sourceAccount);
        transaction.setType(transactionRequest.getType());
        return transaction;
    }
}
