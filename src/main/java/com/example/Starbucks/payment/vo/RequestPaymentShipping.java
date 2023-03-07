package com.example.Starbucks.payment.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestPaymentShipping {
    private Long id;
    private Long userId;
    private Integer shipping_status;
}