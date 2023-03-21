package com.example.Starbucks.category.dto;

import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.category.projection.IProduct;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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
