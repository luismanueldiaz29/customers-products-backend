package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.Product;
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
public class TransactionRequest {
    private TransactionType type;
    private BigDecimal amount;
    private String sourceAccountNumber;
    private String destinationAccountNumber;
}
