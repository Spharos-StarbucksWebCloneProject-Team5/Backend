package com.teamSiHyun.Starbucks.api.payment.vo;

import lombok.*;

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
