package com.example.Starbucks.api.category.dto;

import com.example.Starbucks.api.category.projection.IProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "상품 정보")
public class ResponseSearch {
    @Schema(description = "상품 아이디")
    private Long productId;
    @Schema(description = "상품 이름")
    private String productName;
    @Schema(description = "상품 가격")
    private Integer price;
    @Schema(description = "상품 썸네일 이미지")
    private String thumbnail;
    @Schema(description = "대분류 아이디")
    private Integer mainCategoryId;
    @Schema(description = "중분류 아이디")
    private Integer middleCategoryId;

    public static List<ResponseSearch> ofContents(Page<IProduct> products) {
        return products.getContent().stream()
                .map(element -> ResponseSearch.builder()
                        .productId(element.getId())
                        .productName(element.getName())
                        .price(element.getPrice())
                        .thumbnail(element.getThumbnail())
                        .build()).collect(Collectors.toList());
    }
}
