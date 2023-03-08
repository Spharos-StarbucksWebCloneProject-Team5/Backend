package com.example.Starbucks.event.vo;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEventProduct {
    private Long id;
    private String description;
    private String name;
    private Integer price;
    private String thumbnail;
}
