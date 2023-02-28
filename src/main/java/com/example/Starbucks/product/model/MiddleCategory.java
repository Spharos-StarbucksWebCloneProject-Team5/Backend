package com.example.Starbucks.product.model;

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
    private Category category;//대분류
}
