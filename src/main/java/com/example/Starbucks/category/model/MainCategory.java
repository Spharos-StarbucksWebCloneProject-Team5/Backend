package com.example.Starbucks.category.model;

import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MainCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

}
