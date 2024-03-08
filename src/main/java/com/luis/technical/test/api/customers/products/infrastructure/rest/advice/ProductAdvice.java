package com.luis.technical.test.api.customers.products.infrastructure.rest.advice;

import com.luis.technical.test.api.customers.products.domain.model.dto.response.DefaultResponse;
import com.luis.technical.test.api.customers.products.infrastructure.adapter.exception.ProductException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductAdvice {
    @ExceptionHandler(ProductException.class)
    public ResponseEntity<DefaultResponse<Object>> handleEmptyInput(ProductException customerException){
        DefaultResponse<Object> response = DefaultResponse.builder()
                .error(true)
                .code(HttpStatus.BAD_REQUEST.value())
                .message(customerException.getMessage())
                .body(null)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
