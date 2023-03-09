package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import com.example.Starbucks.cart.vo.ResponseUserCart;

import java.util.List;

public interface ICartService {
    void addCart(RequestCart requestCart);
    void updateCart(RequestUpdateCart requestUpdateCart);
    void deleteCart(Long id);
    void allDeleteCart(Long userId);
    List<ResponseUserCart> getByUserId(Long userId);
//    List<Cart> getAll();
}