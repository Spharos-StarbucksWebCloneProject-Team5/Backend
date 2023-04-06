package com.teamSiHyun.Starbucks.api.category.dto;


import lombok.*;

@Getter
@Setter
public class ResponseCategoryList {
    private Integer id;
    private String name;

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
