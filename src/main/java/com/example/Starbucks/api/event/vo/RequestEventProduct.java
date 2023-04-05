package com.example.Starbucks.api.event.vo;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventProduct {
    @NotNull
    private Long productId;
    @NotNull
    private Long eventId;
}
