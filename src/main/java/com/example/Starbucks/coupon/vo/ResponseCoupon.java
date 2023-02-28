package com.example.Starbucks.coupon.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCoupon {

    private String name;
    private Integer discount;
    private Date end_date;
    private int status;
    private int type;

}
