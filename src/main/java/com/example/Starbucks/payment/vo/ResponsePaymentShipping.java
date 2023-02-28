package com.example.Starbucks.payment.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;

@Getter
@Setter
public class ResponsePaymentShipping {
    private Long id;
    private Integer shipping_status;
}
