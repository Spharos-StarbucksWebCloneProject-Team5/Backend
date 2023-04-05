package com.example.Starbucks.api.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCouponDto {

    @Schema(description = "쿠폰명")
    private String name;

    @Schema(description = "쿠폰 할인율")
    private Integer discount;

    @Schema(description = "쿠폰 만료일")
    private Date end_date;

    @Schema(description = "쿠폰 사용 상태")
    private Integer status;

    @Schema(description = "쿠폰 종류")
    private Integer type;

}
