package com.teamSiHyun.Starbucks.api.coupon.vo;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestCreateCoupon {

    private String name;
    private Integer discount;
    private Date end_date;
    private Integer status;
    private Integer type;
}
