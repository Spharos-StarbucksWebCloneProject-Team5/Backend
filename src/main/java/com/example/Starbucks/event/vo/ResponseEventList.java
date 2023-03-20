package com.example.Starbucks.event.vo;

import lombok.*;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEventList {
    private Long index;
    private Long eventId;
    private String description;
}
