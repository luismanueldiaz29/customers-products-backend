package com.luis.technical.test.api.customers.products.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private String balance;
    private boolean gmfExempt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @OneToOne
    private CustomerEntity customer;
}
