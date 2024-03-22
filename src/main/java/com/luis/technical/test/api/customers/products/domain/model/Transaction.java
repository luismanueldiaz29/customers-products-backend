package com.luis.technical.test.api.customers.products.domain.model;

import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class Transaction {
    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
