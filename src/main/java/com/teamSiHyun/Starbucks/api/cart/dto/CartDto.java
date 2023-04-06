package com.teamSiHyun.Starbucks.api.cart.dto;

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
    @Schema(description = "카트아이디")
    Long cartId;

    @Schema(description = "상품아이디")
    Long productId;

    @Schema(description = "메인카테고리 아이디")
    Integer mainCategoryId;

    @Schema(description = "상품명")
    String productName;

    @Schema(description = "상품가격")
    Integer productPrice;

    @Schema(description = "상품이미지썸네일")
    String productThumbnail;

    @Schema(description = "상품 재고")
    Integer productInventory;

    @Schema(description = "담은 상품 수량")
    Integer count;

    @Builder.Default Boolean checked = false;
}
