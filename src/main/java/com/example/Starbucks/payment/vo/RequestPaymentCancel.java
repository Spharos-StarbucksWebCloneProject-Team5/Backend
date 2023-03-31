package com.example.Starbucks.payment.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestPaymentCancel {
    @NotNull @Min(0)
    private Long id;
}
