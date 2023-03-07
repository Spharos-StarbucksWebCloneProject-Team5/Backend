package com.example.Starbucks.event.model;

import com.example.Starbucks.utility.BaseTimeEntity;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private String title_image;
    private String info_image;
    private Date start_date;
    private Date end_date;
    private boolean isNow;

}
