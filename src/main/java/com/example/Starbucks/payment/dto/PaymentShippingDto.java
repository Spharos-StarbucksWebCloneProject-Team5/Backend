package com.example.Starbucks.payment.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentShippingDto {
    //결제 배송상태 변경
    @NotNull @Min(0)
    private Long id;
    @NotNull @Max(4)
    private Integer shippingStatus;

}

