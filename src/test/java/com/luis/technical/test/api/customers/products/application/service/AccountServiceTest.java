package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.AccountDtoMapper;
import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.constant.AccountConstant;
import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.AccountException;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
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
        // Creación de AccountMock
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

// Creación de AccountResponse
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

// Creación de AccountRequest
        accountRequest = new AccountRequest();
        accountRequest.setAccountType(AccountType.CHECKING_ACCOUNT);
        accountRequest.setStatus(StatusType.ACTIVE);
        accountRequest.setAmount(BigDecimal.valueOf(1000));
        accountRequest.setGmfExempt(true);
        accountRequest.setCustomerId(1L);
    }

//    @Test
//    void testSaveValidCustomer() {
//        when(accountPort.save(any(Account.class))).thenReturn(accountMock);
//        AccountResponse accountResponseOpt = accountService.save(accountRequest);
//        Assertions.assertEquals(accountResponseOpt.getAccountNumber(), accountResponse.getAccountNumber());
//        Assertions.assertEquals(accountResponseOpt.getId(), accountResponse.getId());
//    }
//
//    @Test
//    void testSaveInvalidAccountWhenTheAccountIsZero() {
//        accountRequest.setAmount(BigDecimal.valueOf(0));
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.save(accountRequest));
//        Assertions.assertEquals(AccountConstant.BALANCE_IS_NOT_VALID, accountException.getMessage());
//    }


//    @Test
//    void testUpdateValidAccount() {
//        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
//
//        accountMock.setName("Mateo");
//        accountMock.setLastName("Mateo");
//        accountMock.setBornDate(LocalDate.now().minusYears(20));
//        accountMock.setEmail("mateo@gmail.com");
//        accountMock.setIdentification("1111111111111");
//
//        accountRequest.setName("Mateo");
//        accountRequest.setLastName("Mateo");
//        accountRequest.setBornDate(LocalDate.now().minusYears(20));
//        accountRequest.setEmail("mateo@gmail.com");
//        accountRequest.setIdentification("1111111111111");
//
//        when(accountPort.update(any(Long.class), any(Account.class))).thenReturn(accountMock);
//
//        AccountResponse accountResponseOpt = accountService.update(ID, accountRequest);
//
//        Assertions.assertEquals(accountResponseOpt.getIdentification(), accountMock.getIdentification());
//        Assertions.assertEquals(accountResponseOpt.getId(), accountMock.getId());
//        Assertions.assertEquals(accountResponseOpt.getName(), accountMock.getName());
//        Assertions.assertEquals(accountResponseOpt.getLastName(), accountMock.getLastName());
//        Assertions.assertEquals(accountResponseOpt.getEmail(), accountMock.getEmail());
//        Assertions.assertEquals(accountResponseOpt.getBornDate(), accountMock.getBornDate());
//    }
//
//    @Test
//    void testUpdateInvalidAccountWhenTheAccountIsNotFound() {
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
//        Assertions.assertEquals(AccountConstant.CUSTOMER_NOT_FOUND, accountException.getMessage());
//    }
//
//    @Test
//    void testUpdateInvalidAccountWhenTheAccountIsNotOlder() {
//        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
//        accountRequest.setBornDate(LocalDate.now().minusYears(17));
//
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
//        Assertions.assertEquals(AccountConstant.ADULT_VALIDATION_ERROR, accountException.getMessage());
//    }
//
//    @Test
//    void testUpdateInvalidAccountWhenTheAccountNameIsNotValid() {
//        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
//        accountRequest.setName("");
//        accountRequest.setLastName("");
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
//        Assertions.assertEquals(AccountConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR, accountException.getMessage());
//    }
//
//    @Test
//    void testUpdateInvalidAccountWhenTheAccountEmailIsNotValid() {
//        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
//        accountRequest.setEmail("askaks.com");
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.update(ID, accountRequest));
//        Assertions.assertEquals(AccountConstant.INVALID_EMAIL_FORMAT_ERROR, accountException.getMessage());
//    }
//
//
//    @Test
//    void testDeleteIsInvalidWhenTheAccountIsNoFound() {
//        AccountException accountException = Assertions.assertThrows(AccountException.class, () -> accountService.deleteById(ID));
//        Assertions.assertEquals(AccountConstant.CUSTOMER_NOT_FOUND, accountException.getMessage());
//    }
//
//    @Test
//    void testDeleteIsValid() {
//        when(accountPort.findById(ID)).thenReturn(Optional.of(accountMock));
//        accountService.deleteById(ID);
//        Mockito.verify(accountPort).deleteById(ID);
//    }
//
//    @Test
//    void testFindAllIsValid() {
//        List<Account> accounts = new ArrayList<>();
//        accounts.add(accountMock);
//        accounts.add(accountMock);
//        when(accountPort.findAll()).thenReturn(accounts);
//
//        List<AccountResponse> accountResponses = accountService.findByAll();
//
//        Assertions.assertEquals(accounts.size(), accountResponses.size());
//        for (int i = 0; i < accounts.size(); i++) {
//            AccountResponse expectedResponse = AccountDtoMapper.mapper.toDto(accounts.get(i));
//            AccountResponse actualResponse = accountResponses.get(i);
//            Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
//            Assertions.assertEquals(expectedResponse.getName(), actualResponse.getName());
//        }
//    }
//
//    @Test
//    void testFindById() {
//        when(accountPort.findById(any(Long.class))).thenReturn(Optional.of(accountMock));
//        accountService.findById(ID);
//        Mockito.verify(accountPort).findById(ID);
//    }
}