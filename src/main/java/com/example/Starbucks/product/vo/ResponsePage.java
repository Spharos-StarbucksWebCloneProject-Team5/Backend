package com.example.Starbucks.product.vo;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponsePage {
    private List<Contents> content;
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long totalElements;

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contents {
        private Long productId;
        private String productName;
        private Integer price;
        private String thumbnail;
    }
}
