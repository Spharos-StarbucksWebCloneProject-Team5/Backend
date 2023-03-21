package com.example.Starbucks.payment.repository;

import com.example.Starbucks.category.projection.IProduct;
import com.example.Starbucks.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    List<Payment> findAllByUserId(Long userId);
    @Query(value = "SELECT A.*\n" +
            "  FROM PRODUCT AS A \n" +
            "  JOIN \n" +
            "(SELECT PRODUCT_ID\n" +
            "  FROM PAYMENT\n" +
            "  WHERE PAY_STATUS=1\n" +
            "GROUP BY PRODUCT_ID\n" +
            "ORDER BY SUM(PRODUCT_COUNT) DESC\n" +
            ") B\n" +
            "ON A.ID = B.PRODUCT_ID", nativeQuery = true)
    List<IProduct> bestProduct();
}
