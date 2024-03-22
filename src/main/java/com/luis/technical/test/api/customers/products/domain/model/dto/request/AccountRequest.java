package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import com.luis.technical.test.api.customers.products.domain.model.enums.StatusType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class AccountRequest {
    private AccountType accountType;
    private StatusType status;
    private BigDecimal amount;
    private boolean gmfExempt;
    private Long customerId;
}
