package com.luis.technical.test.api.customers.products.infrastructure.rest.controller;

import com.luis.technical.test.api.customers.products.application.usecase.ProductUseCase;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.AccountRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.AccountResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final ProductUseCase productUseCase;

    public AccountController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping("/{id}")
    public AccountResponse findById(@PathVariable Long id){
        return productUseCase.findById(id).orElse(null);
    }

    @GetMapping
    public List<AccountResponse> findAll() {
        return productUseCase.findByAll();
    }

    @PostMapping()
    public AccountResponse create(@RequestBody AccountRequest productRequest){
        return productUseCase.save(productRequest);
    }

    @PutMapping("/{id}")
    public AccountResponse edit(@RequestBody AccountRequest productRequest,
                                @PathVariable Long id){
        return productUseCase.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productUseCase.deleteById(id);
    }
}
