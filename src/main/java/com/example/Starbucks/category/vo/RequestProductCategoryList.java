package com.example.Starbucks.category.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductCategoryList {
    private Integer categoryId;
    private Integer middleCategoryId;
    private Long productId;
}
