package com.luis.technical.test.api.customers.products.domain.model;

import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
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
public class Customer {
    private Long id;
    private String identificationType;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private LocalDate bornDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Customer(Long id, String identificationType, String identification, String name, String lastName, String email, LocalDate bornDate, LocalDateTime createdAt, LocalDateTime updatedAt) {
//        if (name.length() < 2)
//            throw new IllegalArgumentException(CustomerConstant.MIN_NAME_LENGTH_ERROR);
//
//        if (lastName.length() < 2)
//            throw new IllegalArgumentException(CustomerConstant.MIN_LASTNAME_LENGTH_ERROR);
//
//        if (isOlder(bornDate))
//            throw new IllegalArgumentException(CustomerConstant.ADULT_VALIDATION_ERROR);
//
//        if (!isValidEmail(email))
//            throw new IllegalArgumentException(CustomerConstant.INVALID_EMAIL_FORMAT_ERROR);


        this.id = id;
        this.identificationType = identificationType;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.bornDate = bornDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.identification = identification;
    }

    private boolean isOlder(LocalDate bornDate){
        return Period.between(bornDate, LocalDate.now()).getYears() > 18;
    }

    private boolean isValidEmail(String email) {
        String emailPattern = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
