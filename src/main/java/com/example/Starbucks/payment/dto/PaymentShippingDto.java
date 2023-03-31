package com.example.Starbucks.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "주문 ID")
    private Long paymentId;

    @Schema(description = "배송상태")
    private Integer shippingStatus;

}

