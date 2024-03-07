package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String accountType;
    private String accountNumber;
    private String status;
    private String balance;
    private boolean gmfExempt;
    private Long customerId;
}
