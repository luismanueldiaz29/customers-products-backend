package com.luis.technical.test.api.customers.products.infrastructure.adapter.repository;

import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.AccountEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    List<AccountEntity> findAll();
    Optional<AccountEntity> findByAccountNumber(String accountNumber);
}
