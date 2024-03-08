package com.luis.technical.test.api.customers.products.infrastructure.rest.controller;

import com.luis.technical.test.api.customers.products.application.usecase.TransactionUseCase;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.TransactionRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.TransactionResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionUseCase transactionUseCase;

    public TransactionController(TransactionUseCase transactionUseCase) {
        this.transactionUseCase = transactionUseCase;
    }

    @GetMapping("/{id}")
    public TransactionResponse findById(@PathVariable Long id){
        return transactionUseCase.findById(id).orElse(null);
    }

    @GetMapping
    public List<TransactionResponse> findAll() {
        return transactionUseCase.findAll();
    }

    @PostMapping()
    public TransactionResponse create(@RequestBody TransactionRequest transactionRequest){
        return transactionUseCase.sendTransaction(transactionRequest);
    }
}
