package com.example.Starbucks.event.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAllEventProduct {
    Long id;
    Long productId;
    Long eventId;
}
