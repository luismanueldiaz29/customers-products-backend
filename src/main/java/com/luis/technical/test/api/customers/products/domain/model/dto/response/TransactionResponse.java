package com.luis.technical.test.api.customers.products.domain.model.dto.response;

import com.luis.technical.test.api.customers.products.domain.model.Account;
import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionResponse {
    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
