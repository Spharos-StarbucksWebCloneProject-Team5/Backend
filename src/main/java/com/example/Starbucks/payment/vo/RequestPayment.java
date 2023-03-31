package com.example.Starbucks.payment.vo;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class RequestPayment {
    //받아올 것들

    private Long productId;

    private Integer productCount;

    private Long shippingAddressId;

    private Integer payType;
}
