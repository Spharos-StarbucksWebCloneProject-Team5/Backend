package com.example.Starbucks.payment.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseShipping {
    private Integer preparingProduct;
    private Integer preparingForDelivery;
    private Integer shipping;
    private Integer deliveryCompleted;
}
