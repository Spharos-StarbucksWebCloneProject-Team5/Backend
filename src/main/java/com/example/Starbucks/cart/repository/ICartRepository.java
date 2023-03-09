package com.example.Starbucks.cart.repository;


import com.example.Starbucks.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICartRepository extends JpaRepository<Cart,Long> {
    List<Cart> findAllByUserId(Long userId);
    Cart findByUserIdAndProductId(Long userId, Long productId);
    boolean existsByUserIdAndProductId(Long userId, Long productId);
}
