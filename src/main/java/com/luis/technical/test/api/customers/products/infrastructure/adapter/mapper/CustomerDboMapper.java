package com.luis.technical.test.api.customers.products.infrastructure.adapter.mapper;

import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerDboMapper {
    CustomerDboMapper mapper = Mappers.getMapper(CustomerDboMapper.class);
    CustomerEntity toDbo(Customer entity);
    Customer toDomain(CustomerEntity domain);
}
