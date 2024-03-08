package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private AccountType accountType;
    private StatusType status;
    private BigDecimal amount;
    private boolean gmfExempt;
    private Long customerId;
}
