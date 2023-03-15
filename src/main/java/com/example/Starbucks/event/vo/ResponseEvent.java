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
    private String titleImage;
    private String infoImage;
    private Date startDate;
    private Date endDate;
    private boolean now;
}
