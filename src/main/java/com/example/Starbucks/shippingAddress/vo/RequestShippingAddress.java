package com.example.Starbucks.shippingAddress.vo;

import com.example.Starbucks.users.model.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
