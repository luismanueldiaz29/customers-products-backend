package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.CustomerSpringJpaAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerSpringJpaAdapter customerSpringJpaAdapter;
    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @Test
    void testSave_ValidCustomer() {

    }

}