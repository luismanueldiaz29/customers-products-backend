package com.luis.technical.test.api.customers.products.application.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface CustomerDtoMapper {

    CustomerDtoMapper mapper = Mappers.getMapper(CustomerDtoMapper.class);

    Customer toDomain(CustomerRequest entity);

    CustomerResponse toDto(Customer domain);
}
