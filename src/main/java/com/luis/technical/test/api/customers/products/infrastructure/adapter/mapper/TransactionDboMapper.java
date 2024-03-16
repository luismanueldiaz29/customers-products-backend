package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Transaction;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.TransactionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface TransactionDboMapper {
    TransactionDboMapper mapper = Mappers.getMapper(TransactionDboMapper.class);
    TransactionEntity toDbo(Transaction domain);
    Transaction toDomain(TransactionEntity entity);
}
