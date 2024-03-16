package com.luis.technical.test.api.customers.products.application.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionMapper {
    TransactionMapper mapper = Mappers.getMapper(TransactionMapper.class);
    TransactionResponse toDto(Transaction entity);
    Transaction toDomain(TransactionRequest domain);
}
