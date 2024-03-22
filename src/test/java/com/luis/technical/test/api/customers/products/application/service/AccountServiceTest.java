package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.AccountDtoMapper;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.constant.AccountConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.AccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;
    @Mock
    private AccountPort accountPort;
    private static Long ID = 1L;
    private AccountResponse accountResponse;
    private Account accountMock;
    private AccountRequest accountRequest;

    @BeforeEach
    public void setUp(){
        LocalDateTime createdAt = LocalDateTime.now();
        accountMock = new Account();
        accountMock.setId(1L);
        accountMock.setAccountType(AccountType.CHECKING_ACCOUNT.toString());
        accountMock.setAccountNumber("11111111111");
        accountMock.setStatus(StatusType.ACTIVE.toString());
        accountMock.setAmount(BigDecimal.valueOf(1000));
        accountMock.setGmfExempt(true);
        accountMock.setCreatedAt(createdAt);
        Customer customer = new Customer();
        customer.setId(1L);
        accountMock.setCustomer(customer);

        accountResponse = new AccountResponse();
        accountResponse.setId(1L);
        accountResponse.setAccountType(AccountType.CHECKING_ACCOUNT.toString());
        accountResponse.setAccountNumber("11111111111");
        accountResponse.setStatus(StatusType.ACTIVE.toString());
        accountResponse.setAmount(BigDecimal.valueOf(1000));
        accountResponse.setGmfExempt(true);
        accountResponse.setCreatedAt(createdAt);

        CustomerResponse customerResponse = new CustomerResponse();
        customerResponse.setId(1L);
        accountResponse.setCustomer(customerResponse);

        accountRequest = new AccountRequest();
        accountRequest.setAccountType(AccountType.CHECKING_ACCOUNT);
        accountRequest.setStatus(StatusType.ACTIVE);
        accountRequest.setAmount(BigDecimal.valueOf(1000));
        accountRequest.setGmfExempt(true);
        accountRequest.setCustomerId(1L);
    }

    @Test
    void testSaveValidCustomer() {
        when(accountPort.save(any(Account.class))).thenReturn(accountMock);
        AccountResponse accountResponseOpt = accountService.save(accountRequest);
        Assertions.assertEquals(accountResponseOpt.getAccountNumber(), accountResponse.getAccountNumber());
        Assertions.assertEquals(accountResponseOpt.getId(), accountResponse.getId());
    }

    @Test
    void testSaveInvalidAccountWhenTheAccountIsZero() {
        accountRequest.setAmount(BigDecimal.valueOf(0));
        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.save(accountRequest));
        Assertions.assertEquals(AccountConstant.BALANCE_IS_NOT_VALID, accountException.getMessage());
    }


    @Test
    void testUpdateValidAccount() {
        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
        accountMock.setAccountType(AccountType.CHECKING_ACCOUNT.toString());
        accountRequest.setAccountType(AccountType.CHECKING_ACCOUNT);

        when(accountPort.update(any(Long.class), any(Account.class))).thenReturn(accountMock);
        AccountResponse accountResponseOpt = accountService.update(ID, accountRequest);

        Assertions.assertEquals(accountResponseOpt.getAccountType(), accountMock.getAccountType());
    }

    @Test
    void testUpdateISValidAccountWhenCancelAccountWithBalanceIsZero() {
        accountMock.setAmount(BigDecimal.valueOf(0));
        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
        accountRequest.setStatus(StatusType.CANCEL);
        accountMock.setStatus(StatusType.CANCEL.toString());

        when(accountPort.update(any(Long.class), any(Account.class))).thenReturn(accountMock);
        AccountResponse accountResponseOpt = accountService.update(ID, accountRequest);

        Assertions.assertEquals(accountResponseOpt.getStatus(), accountMock.getStatus());
        Assertions.assertEquals(accountResponseOpt.getAmount(), accountMock.getAmount());
    }

    @Test
    void testUpdateNotValidAccountIsNotFound() {
        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
        Assertions.assertEquals(AccountConstant.ACCOUNT_NOT_FOUND, accountException.getMessage());
    }

    @Test
    void testUpdateNotValidAccountWhenCancelAccountWithBalanceIsZero() {
        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
        accountRequest.setStatus(StatusType.CANCEL);

        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
        Assertions.assertEquals(AccountConstant.ACCOUNT_NOT_CANCEL_BALANCE_IS_NOT_ZERO, accountException.getMessage());
    }

    @Test
    void testUpdateNotValidAccountWhenCancelAccountWithBalanceIsZeroAndRequestIsZero() {
        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
        accountRequest.setStatus(StatusType.CANCEL);
        accountRequest.setAmount(BigDecimal.valueOf(0));

        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
        Assertions.assertEquals(AccountConstant.ACCOUNT_NOT_CANCEL_BALANCE_IS_NOT_ZERO, accountException.getMessage());
    }

    @Test
    void testDeleteIsInvalidWhenTheAccountIsNoFound() {
        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.deleteById(ID));
        Assertions.assertEquals(AccountConstant.ACCOUNT_NOT_FOUND, accountException.getMessage());
    }

    @Test
    void testDeleteIsValid() {
        when(accountPort.findById(ID)).thenReturn(Optional.of(accountMock));
        accountService.deleteById(ID);
        Mockito.verify(accountPort).deleteById(ID);
    }

    @Test
    void testFindAllIsValid() {
        List<Account> accounts = new ArrayList<>();
        accounts.add(accountMock);
        accounts.add(accountMock);
        when(accountPort.findAll()).thenReturn(accounts);

        List<AccountResponse> accountResponses = accountService.findByAll();

        Assertions.assertEquals(accounts.size(), accountResponses.size());
        for (int i = 0; i < accounts.size(); i++) {
            AccountResponse expectedResponse = AccountDtoMapper.MAPPER.toDto(accounts.get(i));
            AccountResponse actualResponse = accountResponses.get(i);
            Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
            Assertions.assertEquals(expectedResponse.getAccountNumber(), actualResponse.getAccountNumber());
        }
    }

    @Test
    void testFindById() {
        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
        accountService.findById(ID);
        Mockito.verify(accountPort).findById(ID);
    }
}