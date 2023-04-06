package com.teamSiHyun.Starbucks.api.gift.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestCreateGift {
    private String sendId;
    private int status;
    private String imgUrl;
    private int payType;
    private int productCount;
}
