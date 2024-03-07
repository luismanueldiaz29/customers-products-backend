package com.luis.technical.test.api.customers.products.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private Long id;
    private String identificationType;
    private String name;
    private String lastName;
    private String mail;
    private LocalDate bornDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
