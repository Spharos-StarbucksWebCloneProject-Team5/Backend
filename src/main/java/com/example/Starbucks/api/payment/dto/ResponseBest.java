package com.example.Starbucks.api.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseBest {
    private Long id;
    private String name;
    private Integer price;
    private String thumbnail;
}
