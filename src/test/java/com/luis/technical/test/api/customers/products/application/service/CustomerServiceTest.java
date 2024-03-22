package com.luis.technical.test.api.customers.products.application.service;

import com.luis.technical.test.api.customers.products.application.mapper.CustomerDtoMapper;
import com.luis.technical.test.api.customers.products.domain.model.Customer;
import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import com.luis.technical.test.api.customers.products.domain.port.CustomerPort;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {
    @Mock
    private CustomerPort customerPort;
    @InjectMocks
    private CustomerService customerService;
    public static final long ID = 1L;
    private CustomerResponse customerResponse;
    private Customer customerMock;
    private CustomerRequest customerRequest;
    @BeforeEach
    public void setUp(){
        LocalDateTime createdAt = LocalDateTime.now();

        customerResponse = new CustomerResponse();
        customerResponse.setId(ID);
        customerResponse.setIdentificationType("CC");
        customerResponse.setIdentification("1003243573");
        customerResponse.setName("Luis Manuel");
        customerResponse.setLastName("Diaz Sequea");
        customerResponse.setEmail("luismanueldiazsequea@gmail.com");
        customerResponse.setBornDate(LocalDate.of(1999, 12, 29));
        customerResponse.setCreatedAt(createdAt);
        customerResponse.setUpdatedAt(null);

        customerMock = new Customer();
        customerMock.setId(ID);
        customerMock.setIdentificationType("CC");
        customerMock.setIdentification("1003243573");
        customerMock.setName("Luis Manuel");
        customerMock.setLastName("Diaz Sequea");
        customerMock.setEmail("luismanueldiazsequea@gmail.com");
        customerMock.setBornDate(LocalDate.of(1999, 12, 29));
        customerMock.setCreatedAt(createdAt);
        customerMock.setUpdatedAt(null);

        customerRequest = new CustomerRequest();
        customerRequest.setIdentificationType("CC");
        customerRequest.setIdentification("1003243573");
        customerRequest.setName("Luis Manuel");
        customerRequest.setLastName("Diaz Sequea");
        customerRequest.setEmail("luismanueldiazsequea@gmail.com");
        customerRequest.setBornDate(LocalDate.of(1999, 12, 29));
    }

    @Test
    void testSaveValidCustomer() {
        when(customerPort.save(any(Customer.class))).thenReturn(customerMock);
        CustomerResponse customerResponseOpt = customerService.save(customerRequest);
        Assertions.assertEquals(customerResponseOpt.getIdentification(), customerResponse.getIdentification());
        Assertions.assertEquals(customerResponseOpt.getId(), customerResponse.getId());
    }

    @DisplayName("""
            Since a client we want to create is a minor.
            when we call at our port
            We hope that the service throws an exception.
            """)
    @Test
    void testSaveInvalidCustomerWhenTheCustomerIsNotOlder() {
        customerRequest.setBornDate(LocalDate.now().minusYears(17));
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.save(customerRequest));
        Assertions.assertEquals(CustomerConstant.ADULT_VALIDATION_ERROR, customerException.getMessage());
    }

    @Test
    void testSaveInvalidCustomerWhenTheCustomerNameIsNotValid() {
        customerRequest.setName("");
        customerRequest.setLastName("");
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.save(customerRequest));
        Assertions.assertEquals(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR, customerException.getMessage());
    }

    @Test
    void testSaveInvalidCustomerWhenTheCustomerEmailIsNotValid() {
        customerRequest.setEmail("askaks.com");
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.save(customerRequest));
        Assertions.assertEquals(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR, customerException.getMessage());
    }

    @Test
    void testUpdateValidCustomer() {
        when(customerPort.findById(any(Long.class))).thenReturn(Optional.of(customerMock));

        customerMock.setName("Mateo");
        customerMock.setLastName("Mateo");
        customerMock.setBornDate(LocalDate.now().minusYears(20));
        customerMock.setEmail("mateo@gmail.com");
        customerMock.setIdentification("1111111111111");

        customerRequest.setName("Mateo");
        customerRequest.setLastName("Mateo");
        customerRequest.setBornDate(LocalDate.now().minusYears(20));
        customerRequest.setEmail("mateo@gmail.com");
        customerRequest.setIdentification("1111111111111");

        when(customerPort.update(any(Long.class), any(Customer.class))).thenReturn(customerMock);

        CustomerResponse customerResponseOpt = customerService.update(ID, customerRequest);

        Assertions.assertEquals(customerResponseOpt.getIdentification(), customerMock.getIdentification());
        Assertions.assertEquals(customerResponseOpt.getId(), customerMock.getId());
        Assertions.assertEquals(customerResponseOpt.getName(), customerMock.getName());
        Assertions.assertEquals(customerResponseOpt.getLastName(), customerMock.getLastName());
        Assertions.assertEquals(customerResponseOpt.getEmail(), customerMock.getEmail());
        Assertions.assertEquals(customerResponseOpt.getBornDate(), customerMock.getBornDate());
    }

    @Test
    void testUpdateInvalidCustomerWhenTheCustomerIsNotFound() {
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.update(ID, customerRequest));
        Assertions.assertEquals(CustomerConstant.CUSTOMER_NOT_FOUND, customerException.getMessage());
    }

    @Test
    void testUpdateInvalidCustomerWhenTheCustomerIsNotOlder() {
        when(customerPort.findById(any(Long.class))).thenReturn(Optional.of(customerMock));
        customerRequest.setBornDate(LocalDate.now().minusYears(17));

        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.update(ID, customerRequest));
        Assertions.assertEquals(CustomerConstant.ADULT_VALIDATION_ERROR, customerException.getMessage());
    }

    @Test
    void testUpdateInvalidCustomerWhenTheCustomerNameIsNotValid() {
        when(customerPort.findById(any(Long.class))).thenReturn(Optional.of(customerMock));
        customerRequest.setName("");
        customerRequest.setLastName("");
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.update(ID, customerRequest));
        Assertions.assertEquals(CustomerConstant.MIN_NAME_OR_LASTNAME_LENGTH_ERROR, customerException.getMessage());
    }

    @Test
    void testUpdateInvalidCustomerWhenTheCustomerEmailIsNotValid() {
        when(customerPort.findById(any(Long.class))).thenReturn(Optional.of(customerMock));
        customerRequest.setEmail("askaks.com");
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.update(ID, customerRequest));
        Assertions.assertEquals(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR, customerException.getMessage());
    }


    @Test
    void testDeleteIsInvalidWhenTheCustomerIsNoFound() {
        CustomerException customerException = Assertions.assertThrows(CustomerException.class, () -> customerService.deleteById(ID));
        Assertions.assertEquals(CustomerConstant.CUSTOMER_NOT_FOUND, customerException.getMessage());
    }

    @Test
    void testDeleteIsValid() {
        when(customerPort.findById(ID)).thenReturn(Optional.of(customerMock));
        customerService.deleteById(ID);
        Mockito.verify(customerPort).deleteById(ID);
    }

    @Test
    void testFindAllIsValid() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customerMock);
        customers.add(customerMock);
        when(customerPort.findAll()).thenReturn(customers);

        List<CustomerResponse> customerResponses = customerService.findByAll();

        Assertions.assertEquals(customers.size(), customerResponses.size());
        for (int i = 0; i < customers.size(); i++) {
            CustomerResponse expectedResponse = CustomerDtoMapper.MAPPER.toDto(customers.get(i));
            CustomerResponse actualResponse = customerResponses.get(i);
            Assertions.assertEquals(expectedResponse.getId(), actualResponse.getId());
            Assertions.assertEquals(expectedResponse.getName(), actualResponse.getName());
        }
    }

    @Test
    void testFindById() {
        when(customerPort.findById(any(Long.class))).thenReturn(Optional.of(customerMock));
        customerService.findById(ID);
        Mockito.verify(customerPort).findById(ID);
    }
}
