package com.teamSiHyun.Starbucks.api.cart.service;

import com.teamSiHyun.Starbucks.api.cart.dto.CartUpdateDto;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestCart;
import com.teamSiHyun.Starbucks.api.cart.dto.CartDto;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestUpdateCart;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ICartService {
    ResponseEntity<?> addCart(Authentication authenticationt, RequestCart requestCart);
    ResponseEntity<?> updateCart(Long id, RequestUpdateCart requestUpdateCart);
    void deleteCart(Long id);
    void allDeleteCart(Authentication authenticationt);
    List<CartDto> getByUserId(Authentication authenticationt);
    CartUpdateDto getCart(Long id);
}