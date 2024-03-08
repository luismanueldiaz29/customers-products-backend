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
    private final AccountDboMapper productDboMapper;

    public AccountSpringJpaAdapter(
            AccountRepository productRepository,
            AccountDboMapper productDboMapper
    ) {
        this.productRepository = productRepository;
        this.productDboMapper = productDboMapper;
    }

    @Override
    public Optional<Account> findById(Long id) {
        return productRepository.findById(id).map(productDboMapper::toDomain);
    }

    @Override
    public Optional<Account> findByAccountNumber(String accountNumber) {
        return productRepository.findByAccountNumber(accountNumber).map(productDboMapper::toDomain);
    }

    @Override
    public Account save(Account product) {
        AccountEntity productNew = productDboMapper.toDbo(product);
        AccountEntity productEntity = productRepository.save(productNew);
        return productDboMapper.toDomain(productEntity);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Account update(Long id, Account product) {
        AccountEntity productNew = productDboMapper.toDbo(product);
        productNew.setId(id);
        AccountEntity productEntity = productRepository.save(productNew);
        return productDboMapper.toDomain(productEntity);
    }

    @Override
    public List<Account> findAll() {
        return productRepository.findAll().stream().map(productDboMapper::toDomain).toList();
    }
}
