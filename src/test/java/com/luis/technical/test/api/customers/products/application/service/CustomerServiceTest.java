package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.CustomerSpringJpaAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class CustomerServiceTest {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerSpringJpaAdapter customerSpringJpaAdapter;
    @Mock
    private CustomerDtoMapper customerDtoMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

//    @Test
//    void testFindById() {
//        CustomerResponse mockedCustomer = CustomerResponse.builder()
//                .id(1L)
//                .email("luis@gmail.com")
//                .name("Luis Manuel")
//                .lastName("Diaz Sequea")
//                .identificationType("CC")
//                .identification("1003243573")
//                .createdAt(LocalDateTime.now())
//                .build();
//        Mockito.when(customerService.findById(1L)).thenReturn(Optional.of(mockedCustomer));
//        CustomerResponse customer = customerService.findById(1L).get();
//        Assertions.assertEquals(mockedCustomer, customer);
//    }
//
//    @Test
//    void testSave_ValidCustomer() {
//        // Implementar pruebas para el método save con un cliente válido
//        CustomerRequest customerRequest = new CustomerRequest();  // Ajusta según tu implementación real
//        Customer mockedDomain = new Customer();  // Ajusta según tu implementación real
//        when(customerDtoMapper.toDomain(customerRequest)).thenReturn(mockedDomain);
//        when(mockedDomain.isOlder()).thenReturn(true);
//        when(mockedDomain.isNameCorrect()).thenReturn(true);
//        when(mockedDomain.isValidEmail()).thenReturn(true);
//
//        LocalDateTime currentTime = LocalDateTime.now();
//        when(customerSpringJpaAdapter.save(mockedDomain)).thenReturn(mockedDomain);
//        when(mockedDomain.getCreatedAt()).thenReturn(currentTime);
//
//        CustomerResponse result = customerService.save(customerRequest);
//
//        assertNotNull(result);
//        assertEquals(currentTime, result.getCreatedAt());
//    }

}