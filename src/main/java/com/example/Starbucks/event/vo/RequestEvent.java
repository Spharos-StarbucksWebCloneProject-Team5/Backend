package com.example.Starbucks.event.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestEvent {
    private String name;
    private String description;
    private String title_image;
    private String info_image;
    private Date start_date;
    private Date end_date;
}
