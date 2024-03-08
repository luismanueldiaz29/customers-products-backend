package com.luis.technical.test.api.customers.products.domain.model.constant;

public class CustomerConstant {
    public static final String MIN_NAME_OR_LASTNAME_LENGTH_ERROR = "El nombre y el apellido deben tener al menos 2 caracteres.";
    public static final String ADULT_VALIDATION_ERROR = "El cliente debe ser mayor de edad.";
    public static final String INVALID_EMAIL_FORMAT_ERROR = "El formato del correo electrónico no es válido.";
    public static final String CUSTOMER_NOT_FOUND = "El cliente no fue encontrado.";

    private CustomerConstant() {}
}
