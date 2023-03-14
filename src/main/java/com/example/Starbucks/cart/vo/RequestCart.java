package com.example.Starbucks.cart.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCart {

    private Long userId;

    private Long productId;

    private Integer count;
}
