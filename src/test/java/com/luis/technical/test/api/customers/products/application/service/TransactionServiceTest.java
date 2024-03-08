package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.TransactionMapper;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.domain.port.TransactionPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.TransactionException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    @Mock
    private TransactionPort transactionPort;

    @Mock
    private TransactionMapper transactionMapper;

    @Mock
    private AccountPort accountPort;

//    @Test
//    void testSendTransactionWithdrawal() {
//        // Mock data
//        TransactionRequest transactionRequest = new TransactionRequest(TransactionType.WITHDRAWAL, BigDecimal.valueOf(100), "3312837942", "5394154488");
//        Account sourceAccount = new Account(1L, AccountType.CHECKING_ACCOUNT.toString(), "3312837942", StatusType.ACTIVE.toString(), BigDecimal.valueOf(200), true, LocalDateTime.now(), null, Customer.builder().id(1L).build());
//
//        when(accountPort.findByAccountNumber("3312837942")).thenReturn(Optional.of(sourceAccount));
//        when(transactionPort.save(any())).thenReturn(new Transaction());
//
//        // Perform the transaction
//        TransactionResponse result = transactionService.sendTransaction(transactionRequest);
//
//        // Verify
//        assertNotNull(result);
//        assertEquals(TransactionType.WITHDRAWAL, result.getType());
//        assertEquals(100.0, result.getAmount());
//
//        verify(accountPort, times(1)).update(eq(sourceAccount.getId()), eq(sourceAccount));
//        verify(transactionPort, times(1)).save(any());
//    }

//    @Test
//    void testSendTransactionDeposit() {
//        // Mock data
//        TransactionRequest transactionRequest = new TransactionRequest("sourceAccountNumber", "destinationAccountNumber", 100.0, TransactionType.DEPOSIT);
//        Account sourceAccount = new Account("sourceAccountNumber", 200.0);
//        Account destinationAccount = new Account("destinationAccountNumber", 300.0);
//
//        when(accountPort.findByAccountNumber("sourceAccountNumber")).thenReturn(Optional.of(sourceAccount));
//        when(accountPort.findByAccountNumber("destinationAccountNumber")).thenReturn(Optional.of(destinationAccount));
//        when(transactionPort.save(any())).thenReturn(new Transaction());
//
//        // Perform the transaction
//        TransactionResponse result = transactionService.sendTransaction(transactionRequest);
//
//        // Verify
//        assertNotNull(result);
//        assertEquals(TransactionType.WITHDRAWAL, result.getType());
//        assertEquals(100.0, result.getAmount());
//
//        verify(accountPort, times(1)).update(eq(sourceAccount.getId()), eq(sourceAccount));
//        verify(accountPort, times(1)).update(eq(destinationAccount.getId()), eq(destinationAccount));
//        verify(transactionPort, times(1)).save(any());
//    }
//
//    @Test
//    void testSendTransactionSourceAccountNotFound() {
//        // Mock data
//        TransactionRequest transactionRequest = new TransactionRequest("nonExistentAccount", null, 100.0, TransactionType.WITHDRAWAL);
//
//        when(accountPort.findByAccountNumber("nonExistentAccount")).thenReturn(Optional.empty());
//
//        // Perform the transaction and verify exception
//        assertThrows(TransactionException.class, () -> transactionService.sendTransaction(transactionRequest));
//
//        // Verify no interaction with ports
//        verifyNoInteractions(transactionPort, accountPort);
//    }
}