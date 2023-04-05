package com.example.Starbucks.api.cart.repository;


import com.example.Starbucks.api.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ICartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserId(Long userId);
    Optional<Cart> findByUserIdAndProductId(Long userId, Long productId);
}
