package com.example.Starbucks.payment.model;

import com.example.Starbucks.utility.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.example.Starbucks.users.model.User;
import com.example.Starbucks.product.model.Product;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;
import org.hibernate.hql.internal.classic.GroupByParser;

import java.time.LocalDateTime;
import java.util.Date;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Payment extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //그룹화 그거 어떻게하는데??
    private Long id;

    @ManyToOne
    private User user;
    // 유저 이름 필요

    @ManyToOne
    private Product product;
    /*
    상품 아이디-가격 필요
    private Integer price;
    price는 product에서 가져오는게 아닌가.....?
     */

    private Integer product_count;
    //구입 갯수

    //private LocalDateTime purchase_date;
    //결제일에 맞춰 자동생성 같은 시간으로 묶어야함

    private String shipping_address;
    //배송지 정보 필요(주소+전화번호) 같이??
    private String shipping_phone;
    //배송연락처

    private Integer shipping_status;
    //배송 상태 정보

    private Integer pay_type;
    //결제 방식
    //private Integer amount;
    //총계.....Long??? 업체 대량구맨가...?

    private Integer pay_status;
    //결제완료, 결제 취소
}
