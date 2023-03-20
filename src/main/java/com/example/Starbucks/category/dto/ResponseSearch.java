package com.example.Starbucks.category.dto;

import lombok.*;
import org.springframework.data.domain.Pageable;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseSearch {
    private Long productId;
    private String productName;
    private Integer price;
    private String thumbnail;
}
