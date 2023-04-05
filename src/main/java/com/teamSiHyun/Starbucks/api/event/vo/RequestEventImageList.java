package com.teamSiHyun.Starbucks.api.event.vo;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventImageList {

    @NotNull
    private Long eventId;

    @NotBlank
    private String image;
}
