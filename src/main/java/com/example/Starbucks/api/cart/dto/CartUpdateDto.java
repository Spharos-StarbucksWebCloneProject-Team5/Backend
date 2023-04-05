package com.example.Starbucks.api.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CartUpdateDto {

    Long productId;

    String productName;

    Integer productPrice;

    String productThumbnail;

    Integer count;
}
