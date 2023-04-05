package com.teamSiHyun.Starbucks.api.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

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

