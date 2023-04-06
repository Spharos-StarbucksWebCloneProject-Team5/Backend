package com.teamSiHyun.Starbucks.api.payment.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    @Schema(description = "결제일자")
    LocalDateTime date;
    @Schema(description = "결제 상태 (0:취소/ 1:완료)")
    Integer payStatus;
    @Schema(description = "상품 ID")
    Long productId;
    @Schema(description = "상품 이름")
    String productName;
    @Schema(description = "상품 가격")
    Integer productPrice;
    @Schema(description = "상품 이미지")
    String productThumbnail;
    @Schema(description = "개수")
    Integer count;
}
