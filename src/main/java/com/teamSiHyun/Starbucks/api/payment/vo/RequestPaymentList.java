package com.teamSiHyun.Starbucks.api.payment.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RequestPaymentList {
    @PastOrPresent
    LocalDate startDate;
    @FutureOrPresent
    LocalDate endDate;

}
