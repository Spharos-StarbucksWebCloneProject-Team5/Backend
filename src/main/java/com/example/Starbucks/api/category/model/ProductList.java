package com.example.Starbucks.api.category.model;

import lombok.*;
import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@ToString
@RedisHash(value = "productList", timeToLive = 10L)
public class ProductList {
    @Id
    private Long id;
    private Long productId;
    private String productName;
    private Integer price;
    private String thumbnail;
}
