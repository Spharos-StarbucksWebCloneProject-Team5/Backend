package com.example.Starbucks.coupon.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CouponDto {

    private Long id;
    private String name;
    private String type;
}
