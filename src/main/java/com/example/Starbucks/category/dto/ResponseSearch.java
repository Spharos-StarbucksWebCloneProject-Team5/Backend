package com.example.Starbucks.category.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.springframework.data.domain.Pageable;

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
}
