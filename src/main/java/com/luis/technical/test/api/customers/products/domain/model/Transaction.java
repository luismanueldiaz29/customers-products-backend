package com.luis.technical.test.api.customers.products.domain.model;

import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private Long id;
    private TransactionType type;
    private BigDecimal amount;
    private Account sourceAccount;
    private Account destinationAccount;
}
