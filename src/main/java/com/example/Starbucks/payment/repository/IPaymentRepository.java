package com.example.Starbucks.payment.repository;

import com.example.Starbucks.payment.model.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Starbucks.product.model.Product;
import com.example.Starbucks.users.model.User;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findAllByProductId(Long productId);
    //Payment getById(Long id);
    //상품아이디로 연결...?
    //Product getAllByProductId(Long productId);
    //리퀘스트 id??
    //Product getPrice(Integer productPrice);

    User findAllByUserId(Long id);
    //user 아이디 받아서 지난 주문내역 다 보기
}
