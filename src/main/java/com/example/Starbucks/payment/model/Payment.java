package com.example.Starbucks.payment.model;

import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.*;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.product.model.Product;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.hql.internal.classic.GroupByParser;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    private Integer productCount;

    private String  receiver;

    private String shippingAddress;

    private String shippingPhone;

    private Integer shippingStatus;

    private Integer payType;

    private Integer amount;

    private Integer payStatus;
    //결제완료, 결제 취소
}
