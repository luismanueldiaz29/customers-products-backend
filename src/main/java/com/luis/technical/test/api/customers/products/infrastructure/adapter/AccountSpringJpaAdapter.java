package com.luis.technical.test.api.customers.products.infrastructure.adapter;

import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.AccountEntity;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper.AccountDboMapper;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountSpringJpaAdapter implements AccountPort {
    private final AccountRepository productRepository;

    public AccountSpringJpaAdapter(
            AccountRepository productRepository
    ) {
        this.productRepository = productRepository;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return productRepository.findById(id).map(AccountDboMapper.MAPPER::toDomain);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return productRepository.findByAccountNumber(accountNumber).map(AccountDboMapper.MAPPER::toDomain);
    }

    @Override
    public Account save(Account product) {
        AccountEntity productNew = AccountDboMapper.MAPPER.toDbo(product);
        AccountEntity productEntity = productRepository.save(productNew);
        return AccountDboMapper.MAPPER.toDomain(productEntity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Account update(Long id, Account product) {
        AccountEntity productNew = AccountDboMapper.MAPPER.toDbo(product);
        productNew.setId(id);
        AccountEntity productEntity = productRepository.save(productNew);
        return AccountDboMapper.MAPPER.toDomain(productEntity);
    }

    @Override
    public List<Account> findAll() {
        return productRepository.findAll().stream().map(AccountDboMapper.MAPPER::toDomain).toList();
    }
}
