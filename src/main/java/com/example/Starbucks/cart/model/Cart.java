package com.example.Starbucks.cart.model;

import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;

    private int count;
}
