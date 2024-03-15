package com.luis.technical.test.api.customers.products.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private final int AGE_OLDER = 18;

    private Long id;
    private String identificationType;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private LocalDate bornDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public boolean isNotOlder(){
        if (this.bornDate == null)
            return true;
        return Period.between(this.bornDate, LocalDate.now()).getYears() < AGE_OLDER;
    }

    public boolean isNotValidEmail() {
        if (email == null) return true;
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(this.email);
        return !matcher.matches();
    }

    public boolean isNotCorrectName() {
        if (this.name == null || this.lastName == null)
            return true;

        return this.name.length() <= 2 || this.lastName.length() <= 2;
    }
}
