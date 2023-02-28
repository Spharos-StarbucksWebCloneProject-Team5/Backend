package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.model.Cart;

import java.util.List;

public interface ICartService {
    void addCart(Cart cart);


    void updateCart(Cart cart);
    List<Cart> getByUserId(Long userId);
    List<Cart> getAll();

}
