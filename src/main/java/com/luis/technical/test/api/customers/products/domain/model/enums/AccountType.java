package com.luis.technical.test.api.customers.products.domain.model.enums;

public enum AccountType {
    CHECKING_ACCOUNT("CUENTA CORRIENTE"),
    SAVING_ACCOUNT("CUENTA DE AHORRO");

    private final String accountType;

    AccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountType(){
        return this.accountType;
    }
}
