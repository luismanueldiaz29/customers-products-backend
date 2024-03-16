package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.AccountEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountDboMapper {
    AccountDboMapper mapper = Mappers.getMapper(AccountDboMapper.class);
    AccountEntity toDbo(Account domain);

    Account toDomain(AccountEntity domain);
}
