package com.teamSiHyun.Starbucks.api.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShippingDto {
    @Schema(description = "상품준비중")
    private Integer preparingProduct;
    @Schema(description = "배송준비중")
    private Integer preparingForDelivery;
    @Schema(description = "배송중")
    private Integer shipping;
    @Schema(description = "배송완료")
    private Integer deliveryCompleted;
}
