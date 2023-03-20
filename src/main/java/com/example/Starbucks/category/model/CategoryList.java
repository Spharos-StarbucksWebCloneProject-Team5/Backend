package com.example.Starbucks.category.model;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.users.model.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryList extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    @ManyToOne(fetch = FetchType.LAZY)
    private MainCategory mainCategory;
    @ManyToOne(fetch = FetchType.LAZY)
    private MiddleCategory middleCategory;//중분류
}
