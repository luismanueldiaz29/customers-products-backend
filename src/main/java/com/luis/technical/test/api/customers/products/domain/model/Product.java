package com.luis.technical.test.api.customers.products.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private String balance;
    private boolean gmfExempt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Customer customer;
}
