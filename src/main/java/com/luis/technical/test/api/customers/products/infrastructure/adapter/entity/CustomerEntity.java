
package com.luis.technical.test.api.customers.products.infrastructure.adapter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String identificationType;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private LocalDate bornDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
