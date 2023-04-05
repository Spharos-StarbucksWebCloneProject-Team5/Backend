package com.example.Starbucks.api.payment.repository;

import com.example.Starbucks.api.category.projection.IProduct;
import com.example.Starbucks.api.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface IPaymentRepository extends JpaRepository<Payment, Long> {
    Payment findById(long id);
    List<Payment> findAllByUserId(Long userId);
    @Query(value = "select p.*\n" +
            "  from product as p \n" +
            "  join \n" +
            "( select a.product_id\n" +
            "  from payment a , category_list b\n" +
            "  where a.product_id = b.product_id\n" +
            "    and pay_status=1\n" +
            "    and b.main_category_id = :main\n" +
            "group by a.product_id\n" +
            "order by sum(a.product_count) desc ) c\n" +
            "on p.id = c.product_id\n" +
            "limit 5", nativeQuery = true)
    List<IProduct> bestProduct(@Param("main") Integer main);
}
