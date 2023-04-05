package com.example.Starbucks.api.coupon.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CouponDto {
    @Schema(description = "쿠폰이름")
    private String name;

    @Schema(description = "할인율")
    private Integer discount;

    @Schema(description = "사용만료일")
    private Date end_date;

    @Schema(description = "사용상태")
    private Integer status;

    @Schema(description = "쿠폰종류")
    private Integer type;
}
