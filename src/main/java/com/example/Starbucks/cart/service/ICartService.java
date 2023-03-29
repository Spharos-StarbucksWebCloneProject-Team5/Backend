package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.dto.CartUpdateDto;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;

import java.util.List;

public interface ICartService {
    void addCart(RequestCart requestCart);
    void updateCart(Long id, RequestUpdateCart requestUpdateCart);
    void deleteCart(Long id);
    void allDeleteCart(Long userId);
    List<CartDto> getByUserId(Long userId);
    CartUpdateDto getCart(Long id);
}