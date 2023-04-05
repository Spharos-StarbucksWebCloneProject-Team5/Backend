package com.example.Starbucks.api.product.model;

import com.example.Starbucks.api.users.model.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String description;
    private String thumbnail;
    private Integer count;
    public void setUpdateCount(Integer count) {
        this.count = count;
    }

    //private Boolean sh;
}
