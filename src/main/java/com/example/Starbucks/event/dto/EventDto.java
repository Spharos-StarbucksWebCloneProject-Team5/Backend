package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import lombok.Data;
@Data

public class EventDto {
    private Long id;
    private String name;


    public EventDto(Long id, String name) {
        this.id = id;
        this.name = name;

    }


}
