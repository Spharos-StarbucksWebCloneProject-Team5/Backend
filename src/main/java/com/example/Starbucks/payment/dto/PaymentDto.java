package com.example.Starbucks.payment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentDto {
    private Long id;
    private Long userId;
    private Long productId;
    private Integer product_count;
    private String shipping_address;
    private String shipping_phone;
    private Integer pay_type;
}
