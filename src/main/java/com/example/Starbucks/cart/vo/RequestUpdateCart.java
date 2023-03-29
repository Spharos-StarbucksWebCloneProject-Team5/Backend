package com.example.Starbucks.cart.vo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class RequestUpdateCart {
    @NotNull @Min(0) @Max(5)
    private Integer count;
}
