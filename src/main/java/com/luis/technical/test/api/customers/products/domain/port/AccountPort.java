package com.luis.technical.test.api.customers.products.domain.port;

import com.luis.technical.test.api.customers.products.domain.model.Account;

import java.util.List;
import java.util.Optional;

public interface AccountPort {
    Optional<Account> findById(Long id);
    Optional<Account> findByAccountNumber(String accountNumber);
    Account save(Account product);
    void deleteById(Long id);
    Account update(Long id, Account product);
    List<Account> findAll();
}
