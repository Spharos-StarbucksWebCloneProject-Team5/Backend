package com.example.Starbucks.category.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseMainCategory {
    private Integer id;
    private String name;
    private String key;

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Side {
        private Integer id;
        private String name;
        private String thumbNail;
    }
}
