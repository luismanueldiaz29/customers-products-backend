package com.luis.technical.test.api.customers.products.infrastructure.rest.controller;

import com.luis.technical.test.api.customers.products.application.usecase.CustomerUseCase;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.CustomerRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.CustomerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerUseCase customerUseCase;

    public CustomerController(CustomerUseCase customerUseCase) {
        this.customerUseCase = customerUseCase;
    }

    @GetMapping("/{id}")
    public CustomerResponse findById(@PathVariable Long id){
        return customerUseCase.findById(id).orElse(null);
    }

    @GetMapping
    public List<CustomerResponse> findAll() {
        return customerUseCase.findByAll();
    }

    @PostMapping()
    public CustomerResponse create(@RequestBody CustomerRequest customerRequest){
        return customerUseCase.save(customerRequest);
    }

    @PutMapping("/{id}")
    public CustomerResponse edit(@RequestBody CustomerRequest customerRequest,
                        @PathVariable Long id){
        return customerUseCase.update(id, customerRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        customerUseCase.deleteById(id);
    }
}
