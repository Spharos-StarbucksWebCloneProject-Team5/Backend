package com.example.Starbucks.api.product.vo;

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

    private Integer count;
    //private Boolean show;
}
