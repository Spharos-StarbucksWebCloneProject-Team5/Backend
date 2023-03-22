package com.example.Starbucks.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProductList {
    private Long id;
    private String name;
    private Integer price;
    private String thumbnail;
}
