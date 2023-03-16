package com.example.Starbucks.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserShippingDto {
    private Integer preparingProduct;
    private Integer preparingForDelivery;
    private Integer shipping;
    private Integer deliveryCompleted;
}
