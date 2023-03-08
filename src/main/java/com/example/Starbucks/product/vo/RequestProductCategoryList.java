package com.example.Starbucks.product.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestProductCategoryList {
    private Integer categoryId;
    private Integer middleCategoryId;
    private Long productId;
}
