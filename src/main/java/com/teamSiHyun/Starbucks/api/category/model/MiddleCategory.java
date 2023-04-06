package com.teamSiHyun.Starbucks.api.category.model;

import com.teamSiHyun.Starbucks.api.users.model.utility.BaseTimeEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MiddleCategory extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    private MainCategory mainCategory;//대분류
}
