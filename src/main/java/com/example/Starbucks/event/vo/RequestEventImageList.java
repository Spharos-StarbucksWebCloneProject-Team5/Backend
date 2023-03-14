package com.example.Starbucks.event.vo;

import com.example.Starbucks.event.model.Event;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventImageList {
    @NotBlank
    private Long eventId;

    @NotBlank
    private String image;
}
