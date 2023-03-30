package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.dto.CartUpdateDto;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestSelecetDelets;
import com.example.Starbucks.cart.vo.RequestUpdateCart;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICartService {
    void addCart(HttpServletRequest httpServletRequest, RequestCart requestCart);
    void updateCart(Long id, RequestUpdateCart requestUpdateCart);
    void deleteCart(Long id);
    void allDeleteCart(HttpServletRequest httpServletRequest);
    List<CartDto> getByUserId(HttpServletRequest httpServletRequest);
    CartUpdateDto getCart(Long id);
}