package com.example.Starbucks.payment.vo;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@ToString
public class RequestPayment {
    //받아올 것들
    private Long userId;
    private Long productId;
    private Integer product_count;
    private String shipping_address;
    private String shipping_phone;
    private Integer payType;
    private boolean isGift;
}
