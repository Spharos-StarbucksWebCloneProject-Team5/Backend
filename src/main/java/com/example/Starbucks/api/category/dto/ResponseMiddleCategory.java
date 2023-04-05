package com.example.Starbucks.api.category.dto;

import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseMiddleCategory {
    private Integer id;
    private String name;
    private List<Data> data;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Data {
        private Integer id;
        private String name;
        private String key;
    }
}
