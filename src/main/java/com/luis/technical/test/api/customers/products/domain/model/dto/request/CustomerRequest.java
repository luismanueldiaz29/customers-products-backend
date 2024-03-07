package com.luis.technical.test.api.customers.products.domain.model.dto.request;

import com.luis.technical.test.api.customers.products.domain.model.constant.CustomerConstant;
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
public class CustomerRequest {
    private String identificationType;
    private String identification;
    private String name;
    private String lastName;
    private String email;
    private LocalDate bornDate;
}
