package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.dto.CartUpdateDto;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ICartService {
    void addCart(Authentication authenticationt, RequestCart requestCart);
    void updateCart(Long id, RequestUpdateCart requestUpdateCart);
    void deleteCart(Long id);
    void allDeleteCart(Authentication authenticationt);
    List<CartDto> getByUserId(Authentication authenticationt);
    CartUpdateDto getCart(Long id);
}