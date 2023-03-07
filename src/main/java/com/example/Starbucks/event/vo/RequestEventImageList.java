package com.example.Starbucks.event.vo;

import com.example.Starbucks.event.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestEventImageList {
    private Long eventId;

    private String image;
}
