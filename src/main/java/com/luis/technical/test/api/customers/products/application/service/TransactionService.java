package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.usecase.TransactionUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Product;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.constant.TransactionConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.port.ProductPort;
import com.luis.technical.test.api.customers.products.domain.port.TransactionPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.TransactionException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService implements TransactionUseCase {
    private final TransactionPort transactionPort;
    private final ProductPort productPort;

    public TransactionService(TransactionPort transactionPort, ProductPort productPort) {
        this.transactionPort = transactionPort;
        this.productPort = productPort;
    }


    @Override
    public boolean sendTransaction(TransactionRequest transactionRequest) {
        Optional<Product> sourceAccount = productPort.findByAccountNumber(transactionRequest.getSourceAccountNumber());
        Optional<Product> destinationAccount = productPort.findByAccountNumber(transactionRequest.getDestinationAccountNumber());

        if (sourceAccount.isEmpty())
            throw new TransactionException(TransactionConstant.ACCOUNT_NOT_HAVE_ENOUGH_BALANCE);
        if (destinationAccount.isEmpty())
            throw new TransactionException(TransactionConstant.ACCOUNT_DESTINATION_NOT_FOUND);
        if (sourceAccount.get().isBalanceGreaterThan(transactionRequest.getAmount()))
            throw new TransactionException(TransactionConstant.ACCOUNT_NOT_HAVE_ENOUGH_BALANCE);

        sourceAccount.get().subtract(transactionRequest.getAmount());
        destinationAccount.get().plus(transactionRequest.getAmount());

        productPort.update(sourceAccount.get().getId(), sourceAccount.get());
        productPort.update(destinationAccount.get().getId(), destinationAccount.get());

        Transaction transaction = Transaction.builder()
                .amount(transactionRequest.getAmount())
                .destinationAccount(destinationAccount.get())
                .sourceAccount(sourceAccount.get())
                .type(transactionRequest.getType())
                .build();

        transactionPort.save(transaction);
        return true;
    }
}
