package com.example.Starbucks.event.model;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EventImageList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    private String image;
}
