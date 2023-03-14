package com.example.Starbucks.product.vo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestProduct {
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;
    private boolean isShow;
}
