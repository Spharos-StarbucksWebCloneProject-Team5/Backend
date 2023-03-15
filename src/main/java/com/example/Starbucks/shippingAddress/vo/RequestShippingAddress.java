package com.example.Starbucks.shippingAddress.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestShippingAddress {
    private String Address;
    private int main;
    private Boolean choice;
}
