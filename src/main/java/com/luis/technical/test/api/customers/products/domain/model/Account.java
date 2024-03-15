package com.luis.technical.test.api.customers.products.domain.model;

import com.luis.technical.test.api.customers.products.domain.model.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private static final String PREFIX_CHECKING_ACCOUNT = "33";
    private static final String PREFIX_SAVING_ACCOUNT = "53";
    private Long id;
    private String accountType;
    private String accountNumber;
    private String status;
    private BigDecimal amount;
    private boolean gmfExempt;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Customer customer;

    public boolean balanceIsNotValid(){
        return amount.longValue() < 0;
    }

    public String generateAccountNumber() {
        if (accountType.equals(AccountType.CHECKING_ACCOUNT.toString())) {
            return PREFIX_CHECKING_ACCOUNT + generateRandomNumber();
        } else if (accountType.equals(AccountType.SAVING_ACCOUNT.toString())) {
            return PREFIX_SAVING_ACCOUNT + generateRandomNumber();
        }
        return "";
    }

    private String generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(90000000) + 10000000; // Rango de 10000000 a 99999999
        return String.valueOf(randomNumber);
    }

    public boolean canInactivateAccount(){
        return amount.longValue() == 0;
    }

    public boolean isBalanceGreaterThan(BigDecimal anotherAmount) {
        return this.amount.compareTo(anotherAmount) >= 0;
    }

    public void plus(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    public void subtract(BigDecimal amount) {
        this.amount = this.amount.subtract(amount);
    }
}
