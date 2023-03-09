package com.example.Starbucks.product.vo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseProduct {
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;
    private boolean isShow;
}
