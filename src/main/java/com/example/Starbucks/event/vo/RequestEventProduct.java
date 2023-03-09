package com.example.Starbucks.event.vo;


import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventProduct {
    private Long productId;
    private Long eventId;
}
