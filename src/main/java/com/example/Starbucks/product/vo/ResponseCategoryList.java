package com.example.Starbucks.product.vo;


import lombok.*;

@Getter
@Setter
public class ResponseCategoryList {
    private Integer id;
    private String name;
    //private String type;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static class categorySearchInfo {
        private Long productId;
        private String productName;
        private Integer price;
        private String thumbnail;
    }
}
