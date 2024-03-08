package com.luis.technical.test.api.customers.products.domain.model.enums;

public enum StatusType {
    ACTIVE("ACTIVO"),
    INACTIVE("INACTIVO"),
    CANCEL("CANCELADA");

    private final String statusType;

    StatusType(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType(){
        return this.statusType;
    }
}
