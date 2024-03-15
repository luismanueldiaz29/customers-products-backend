package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.AccountDtoMapper;
import com.luis.technical.test.api.customers.products.application.usecase.ProductUseCase;
import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.constant.AccountConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import com.luis.technical.test.api.customers.products.domain.port.AccountPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.AccountException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements ProductUseCase {
    private final AccountPort productPort;
    private final AccountDtoMapper productDtoMapper;

    public AccountService(
            AccountPort productPort,
            AccountDtoMapper productDtoMapper
    ) {
        this.productPort = productPort;
        this.productDtoMapper = productDtoMapper;
    }

    @Override
    public Optional<AccountResponse> findById(Long id) {
        return productPort.findById(id).map(productDtoMapper::toDto);
    }

    @Override
    public AccountResponse save(AccountRequest productRequest) {
        Account productNew = productDtoMapper.toDomain(productRequest);

        if (productNew.balanceIsNotValid())
            throw new AccountException(AccountConstant.BALANCE_IS_NOT_VALID);

        productNew.setCreatedAt(LocalDateTime.now());
        productNew.setAccountNumber(productNew.generateAccountNumber());
        productNew.setStatus(StatusType.ACTIVE.toString());
        Account product = productPort.save(productNew);
        return productDtoMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Account> productResponse = productPort.findById(id);
        if (productResponse.isEmpty())
            throw new CustomerException(AccountConstant.PRODUCT_NOT_FOUND);

        productPort.deleteById(id);
    }

    @Override
    public AccountResponse update(Long id, AccountRequest productRequest) {
        Optional<Account> productResponse = productPort.findById(id);
        if (productResponse.isEmpty())
            throw new CustomerException(AccountConstant.PRODUCT_NOT_FOUND);

        Account productUpdate = productDtoMapper.toDomain(productRequest);
        if (productUpdate.balanceIsNotValid())
            throw new AccountException(AccountConstant.BALANCE_IS_NOT_VALID);
        if (productUpdate.getStatus().equals(StatusType.INACTIVE.toString()) && !productResponse.get().canInactivateAccount())
            throw new CustomerException(AccountConstant.ACCOUNT_NOT_INACTIVE_BALANCE_IS_NOT_ZERO);

        productUpdate.setAccountNumber(productResponse.get().getAccountNumber());
        productUpdate.setCreatedAt(productResponse.get().getCreatedAt());
        productUpdate.setUpdatedAt(LocalDateTime.now());

        Account product = productPort.update(id, productUpdate);
        return productDtoMapper.toDto(product);
    }

    @Override
    public List<AccountResponse> findByAll() {
        return productPort.findAll().stream().map(productDtoMapper::toDto).toList();
    }
}
