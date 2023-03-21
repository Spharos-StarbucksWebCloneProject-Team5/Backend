package com.example.Starbucks.cart.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartDto {

    @Schema(description = "상품아이디")
    Long productId;

    @Schema(description = "상품명")
    String productName;

    @Schema(description = "상품가격")
    Integer productPrice;

    @Schema(description = "상품이미지썸네일")
    String productThumbnail;

    @Schema(description = "담은 상품 수량")
    Integer count;
}
