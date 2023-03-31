package com.example.Starbucks.cart.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class RequestCart {
    @NotNull
    private Long productId;
    @NotNull @Min(0) @Max(5)
    private Integer count;
}
