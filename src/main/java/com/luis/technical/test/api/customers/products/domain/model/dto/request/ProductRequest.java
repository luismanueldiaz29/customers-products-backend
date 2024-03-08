package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private AccountType accountType;
    private String accountNumber;
    private StatusType status;
    private String balance;
    private boolean gmfExempt;
    private Long customerId;
}
