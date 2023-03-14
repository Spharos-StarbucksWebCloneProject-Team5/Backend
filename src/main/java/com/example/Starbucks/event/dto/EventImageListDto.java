package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageListDto {


    private Long id;

    private Event event;

    private String image;
}
