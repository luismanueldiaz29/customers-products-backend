package com.luis.technical.test.api.customers.products.domain.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DefaultResponse<T> {
    private boolean error;
    private String message;
    private int code;
    private T body;
}
