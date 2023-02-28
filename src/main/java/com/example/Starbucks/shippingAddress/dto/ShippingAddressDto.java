package com.example.Starbucks.shippingAddress.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShippingAddressDto {

    private Long id;
    private String Address;
    private int isMain;
}
