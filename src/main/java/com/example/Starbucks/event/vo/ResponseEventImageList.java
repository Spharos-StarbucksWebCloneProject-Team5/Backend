package com.example.Starbucks.event.vo;

import com.example.Starbucks.event.model.Event;
import lombok.*;

import javax.persistence.ManyToOne;
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseEventImageList {
    private Long id;
    private Long eventId;
    private String image;
}
