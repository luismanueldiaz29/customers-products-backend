package com.luis.technical.test.api.customers.products.application.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface AccountDtoMapper {
    AccountDtoMapper mapper = Mappers.getMapper(AccountDtoMapper.class);
    AccountResponse toDto(Account entity);
    Account toDomain(AccountRequest domain);
}
