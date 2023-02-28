package com.example.Starbucks.gift.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseGift {
    private String sendId;
    private int status;
    private String imgUrl;
    private int payType;
    private int productCount;
}
