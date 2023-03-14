package com.example.Starbucks.event.vo;


import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventProduct {
    @NotBlank
    private Long productId;
    @NotBlank
    private Long eventId;
}
