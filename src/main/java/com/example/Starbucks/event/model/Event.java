package com.example.Starbucks.event.model;

import com.example.Starbucks.users.model.utility.BaseTimeEntity;
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
    private String titleImage;
    private String infoImage;
    private Date startDate;
    private Date endDate;
    private Boolean now;

}
