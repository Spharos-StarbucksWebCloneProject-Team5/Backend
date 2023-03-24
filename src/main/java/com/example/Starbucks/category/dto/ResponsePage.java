package com.example.Starbucks.category.dto;

import com.example.Starbucks.category.projection.IProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "상품 정보 pageable 반환")
public class ResponsePage {
    @Schema(description = "상품 정보")
    private List<Object> content;
    @Schema(description = "현재 페이지")
    private Integer pageNum;
    @Schema(description = "페이지당 element 크기")
    private Integer pageSize;
    @Schema(description = "총 페이지 수")
    private Integer totalPage;
    @Schema(description = "총 element 수")
    private Long totalElements;

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Contents {
        @Schema(description = "상품 아이디")
        private Long productId;
        @Schema(description = "상품 이름")
        private String productName;
        @Schema(description = "상품 가격")
        private Integer price;
        @Schema(description = "상품 썸네임 이미지")
        private String thumbnail;
    }


    public static List<Contents> ofContents(List<IProduct> products) {
        return products.stream()
                .map(element -> ResponsePage.Contents.builder()
                        .productId(element.getId())
                        .productName(element.getName())
                        .price(element.getPrice())
                        .thumbnail(element.getThumbnail())
                        .build()).collect(Collectors.toList());
    }
}
