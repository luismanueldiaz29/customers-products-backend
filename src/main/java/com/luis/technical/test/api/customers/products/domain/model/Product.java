package com.luis.technical.test.api.customers.products.domain.model;

import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Random;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private double balance;
    private boolean gmfExempt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Customer customer;

    public boolean balanceIsValid(){
        return balance >= 0;
    }

    public String generateAccountNumber() {
        if (accountType.equals(AccountType.CHECKING_ACCOUNT.toString())) {
            return "33" + generarNumeroAleatorio();
        } else if (accountType.equals(AccountType.SAVINGS_ACCOUNT.toString())) {
            return  "53" + generarNumeroAleatorio();
        }
        return "";
    }

    private String generarNumeroAleatorio() {
        Random random = new Random();
        int numeroAleatorio = random.nextInt(90000000) + 10000000; // Rango de 10000000 a 99999999
        return String.valueOf(numeroAleatorio);
    }

    public boolean canInactivateAccount(){
        return balance == 0;
    }
}
