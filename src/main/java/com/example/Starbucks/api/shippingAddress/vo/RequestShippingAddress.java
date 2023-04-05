package com.example.Starbucks.api.shippingAddress.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestShippingAddress {

    private String nickname;

    private String  receiver;

    private Integer zipCode;

    private String address;

    private String detailAddress;

    private String shippingPhone1;

    private String shippingPhone2;

    private String shippingMemo;

    private Boolean choiceMain;
}
