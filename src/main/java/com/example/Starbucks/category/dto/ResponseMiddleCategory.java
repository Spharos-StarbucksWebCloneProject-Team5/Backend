package com.example.Starbucks.category.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMiddleCategory {
    private Integer id;
    private String name;
    private Integer mainCategoryId;//대분류
}
