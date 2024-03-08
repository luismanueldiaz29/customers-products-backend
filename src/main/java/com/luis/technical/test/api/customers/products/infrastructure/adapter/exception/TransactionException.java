package com.luis.technical.test.api.customers.products.infrastructure.adapter.exception;

public class TransactionException extends RuntimeException{
    public TransactionException(String message){
        super(message);
    }
}
