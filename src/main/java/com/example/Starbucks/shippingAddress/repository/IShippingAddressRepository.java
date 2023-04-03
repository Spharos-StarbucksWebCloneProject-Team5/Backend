package com.example.Starbucks.shippingAddress.repository;

import com.example.Starbucks.product.model.ProductImageList;
import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface IShippingAddressRepository extends JpaRepository<ShippingAddress,Long> {
    List<ShippingAddress> findAllByUserId(Long userId);


    @Modifying
    @Transactional
    @Query(value = "update shipping_address s " +
            "set s.choice_main = 0 " +
            "where s.user_id = :userId",nativeQuery = true)
    void mainChange(@Param("userId") Long userId);

    Optional<ShippingAddress> findByUserIdAndChoiceMain(Long userId, Boolean main);


}
