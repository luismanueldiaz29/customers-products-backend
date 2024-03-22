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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements ProductUseCase {
    private final AccountPort accountPort;

    public AccountService(
            AccountPort accountPort
    ) {
        this.accountPort = accountPort;
    }

    @Override
    public Optional<AccountResponse> findById(Long id) {
        return accountPort.findById(id).map(AccountDtoMapper.MAPPER::toDto);
    }

    @Override
    public AccountResponse save(AccountRequest accountRequest) {
        Account accountNew = AccountDtoMapper.MAPPER.toDomain(accountRequest);

        if (accountNew.balanceIsNotValid())
            throw new AccountException(AccountConstant.BALANCE_IS_NOT_VALID);

        accountNew.setCreatedAt(LocalDateTime.now());
        accountNew.setAccountNumber(accountNew.generateAccountNumber());
        accountNew.setStatus(StatusType.ACTIVE.toString());
        Account account = accountPort.save(accountNew);
        return AccountDtoMapper.MAPPER.toDto(account);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Account> accountResponse = accountPort.findById(id);
        if (accountResponse.isEmpty())
            throw new AccountException(AccountConstant.ACCOUNT_NOT_FOUND);

        accountPort.deleteById(id);
    }

    @Override
    public AccountResponse update(Long id, AccountRequest accountRequest) {
        Optional<Account> accountResponse = accountPort.findById(id);
        if (accountResponse.isEmpty())
            throw new AccountException(AccountConstant.ACCOUNT_NOT_FOUND);
        Account accountUpdate = AccountDtoMapper.MAPPER.toDomain(accountRequest);
        if (accountUpdate.getStatus().equals(StatusType.CANCEL.toString()) && isValidInactivate(accountRequest, accountResponse.get()))
            throw new AccountException(AccountConstant.ACCOUNT_NOT_CANCEL_BALANCE_IS_NOT_ZERO);

        accountUpdate.setAccountNumber(accountResponse.get().getAccountNumber());
        accountUpdate.setCreatedAt(accountResponse.get().getCreatedAt());
        accountUpdate.setUpdatedAt(LocalDateTime.now());

        Account account = accountPort.update(id, accountUpdate);
        return AccountDtoMapper.MAPPER.toDto(account);
    }

    private boolean isValidInactivate(AccountRequest accountRequest, Account accountResponse) {
        return !accountResponse.canInactivateAccount() || accountRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

    @Override
    public List<AccountResponse> findByAll() {
        return accountPort.findAll().stream().map(AccountDtoMapper.MAPPER::toDto).toList();
    }
}
