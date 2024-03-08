package com.luis.technical.test.api.customers.products.infrastructure.adapter.exception;

public class ApiUnprocessableEntityException extends RuntimeException{
    public ApiUnprocessableEntityException(String message){
        super(message);
    }
}
