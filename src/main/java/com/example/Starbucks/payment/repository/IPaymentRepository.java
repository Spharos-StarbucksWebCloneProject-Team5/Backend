package com.example.Starbucks.payment.repository;

import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    List<Payment> findAllByUserId(Long userId);
    @Query(value = "SELECT P.*\n" +
            "  FROM PRODUCT AS P \n" +
            "  JOIN \n" +
            "( SELECT A.PRODUCT_ID\n" +
            "  FROM PAYMENT A , CATEGORY_LIST B\n" +
            "  WHERE A.PRODUCT_ID = B.PRODUCT_ID\n" +
            "    AND PAY_STATUS=1\n" +
            "    AND B.MAIN_CATEGORY_ID = :main\n" +
            "GROUP BY A.PRODUCT_ID\n" +
            "ORDER BY SUM(A.PRODUCT_COUNT) DESC ) C\n" +
            "ON P.ID = C.PRODUCT_ID\n" +
            "LIMIT 5", nativeQuery = true)
    List<IProduct> bestProduct(@Param("main") Integer main);
}
