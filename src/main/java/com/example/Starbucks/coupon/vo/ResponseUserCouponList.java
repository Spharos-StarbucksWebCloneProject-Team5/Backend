package com.example.Starbucks.coupon.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUserCouponList {
    private Long id;
    private Long userId;
    private Long couponId;
}
