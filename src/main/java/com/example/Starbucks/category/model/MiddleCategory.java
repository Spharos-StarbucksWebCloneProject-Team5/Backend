package com.example.Starbucks.category.model;

import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class MiddleCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private MainCategory mainCategory;//대분류
}
