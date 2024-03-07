package com.luis.technical.test.api.customers.products.domain.model.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
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
public class CustomerResponse {
    private Long id;
    private String identificationType;
    private String name;
    private String lastName;
    private String email;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bornDate;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updatedAt;
}
