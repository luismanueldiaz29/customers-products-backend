package com.luis.technical.test.api.customers.products.infrastructure.adapter.repository;

import com.luis.technical.test.api.customers.products.infrastructure.adapter.entity.CustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
}
