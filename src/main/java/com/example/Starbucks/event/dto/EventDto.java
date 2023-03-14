package com.example.Starbucks.event.dto;

import com.example.Starbucks.event.model.Event;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventDto {

    private Long id;

    private String name;

    private String description;

    private String title_image;

    private String info_image;

    private Date start_date;

    private Date end_date;

    private boolean isNow;


}
