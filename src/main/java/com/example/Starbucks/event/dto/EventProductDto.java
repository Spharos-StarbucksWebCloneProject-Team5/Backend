package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.product.model.Product;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventProductDto {

    @Schema(description = "이벤트상품 아이디")
    private Long id;

    @Schema(description = "이벤트상품 설명")
    private String description;

    @Schema(description = "이벤트상품명")
    private String name;

    @Schema(description = "이벤트상품 가격")
    private Integer price;

    @Schema(description = "이벤트상품 썸네일")
    private String thumbnail;

}
