package com.example.Starbucks.product.vo;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ResponseMiddleCategory {
    private Integer id;
    private String name;
    private Integer mainCategoryId;//대분류
}
