package com.example.Starbucks.event.vo;

import com.example.Starbucks.event.model.Event;
import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestEventImageList {
    private Long eventId;

    private String image;
}
