package com.example.Starbucks.event.vo;

import com.example.Starbucks.event.model.Event;
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
