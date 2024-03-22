package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.enums.TransactionType;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequest {
    private TransactionType type;
    private BigDecimal amount;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
}
