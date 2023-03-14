package com.example.Starbucks.shippingAddress.vo;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseShippingAddress {
    private Long id;
    private String address;
    private int isMain;
    private boolean isShow;
}
