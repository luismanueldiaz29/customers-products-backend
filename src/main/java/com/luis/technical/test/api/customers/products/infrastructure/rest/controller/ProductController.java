package com.luis.technical.test.api.customers.products.infrastructure.rest.controller;

import com.luis.technical.test.api.customers.products.application.usecase.ProductUseCase;
import com.luis.technical.test.api.customers.products.domain.model.dto.request.ProductRequest;
import com.luis.technical.test.api.customers.products.domain.model.dto.response.ProductResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductUseCase productUseCase;

    public ProductController(ProductUseCase productUseCase) {
        this.productUseCase = productUseCase;
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id){
        return productUseCase.findById(id).get();
    }

    @GetMapping
    public List<ProductResponse> getAll() {
        return productUseCase.findByAll();
    }

    @PostMapping()
    public ProductResponse create(@RequestBody ProductRequest productRequest){
        return productUseCase.save(productRequest);
    }

    @PutMapping("/{id}")
    public ProductResponse edit(@RequestBody ProductRequest productRequest,
                        @PathVariable Long id){
        return productUseCase.update(id, productRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id){
        productUseCase.deleteById(id);
    }
}
