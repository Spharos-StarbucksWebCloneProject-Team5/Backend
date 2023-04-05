package com.example.Starbucks.api.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPaymentList {
    @PastOrPresent
    LocalDate startDate;
    @FutureOrPresent
    LocalDate endDate;

}
