package com.luis.technical.test.api.customers.products.domain.model.constant;

public class AccountConstant {
    public static final String PRODUCT_NOT_FOUND = "La cuenta no fue encontrada.";
    public static final String BALANCE_IS_NOT_VALID = "La cuenta no puede tener un valor menor a 0.";
    public static final String ACCOUNT_NOT_INACTIVE_BALANCE_IS_NOT_ZERO = "Solo se puede inactivar la cuenta si esta con saldo igual a 0.";

    private AccountConstant(){}
}
