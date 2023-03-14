package com.example.Starbucks.event.vo;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEvent {
    private Long id;
    private String name;
    private String description;
    private String title_image;
    private String info_image;
    private Date start_date;
    private Date end_date;
    private boolean isNow;
}
