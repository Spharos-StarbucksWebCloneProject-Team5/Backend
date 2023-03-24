package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class CartServiceImpl implements ICartService{
    private final ICartRepository iCartRepository;
    private final UserRepository userRepository;
    private final IProductRepository iProductRepository;

    @Override
    public void addCart(RequestCart requestCart) {
        //장바구니에 담았던 상품일 시 수량 추가
        Optional<Cart> cart = iCartRepository.findByUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
        log.info("{}",cart);
        if(cart.isPresent()){
            iCartRepository.save(Cart.builder()
                            .id(cart.get().getId())
                            .user(cart.get().getUser())
                            .product(cart.get().getProduct())
                            .count(cart.get().getCount() + requestCart.getCount())
                            .now(true)
                    .build());
        } else {
            iCartRepository.save(Cart.builder()
                    .user(userRepository.findById(requestCart.getUserId()).get())
                    .product(iProductRepository.findById(requestCart.getProductId()).get())
                    .count(requestCart.getCount())
                    .now(true)
                    .build());
        }
    }

    @Override
    public void updateCart(RequestUpdateCart requestUpdateCart){
        Cart cart = iCartRepository.findById(requestUpdateCart.getId()).get();
        iCartRepository.save(Cart.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .product(cart.getProduct())
                .count(requestUpdateCart.getCount())
                .now(true)
                .build());
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = iCartRepository.findById(id).get();
        iCartRepository.save(Cart.builder()
                .id(cart.getId())
                .user(cart.getUser())
                .product(cart.getProduct())
                .count(0)
                .now(false)
                .build());
    }
    @Override
    public void allDeleteCart(Long userId) {
        List<Cart> carts = iCartRepository.findAllByUserId(userId);
        for(Cart cart : carts){
            iCartRepository.save(Cart.builder()
                    .id(cart.getId())
                    .user(cart.getUser())
                    .product(cart.getProduct())
                    .count(0)
                    .now(false)
                    .build());
        }
    }

    @Override
    public List<CartDto> getByUserId(Long userId) {
        List<CartDto> userCarts = iCartRepository.findAllByUserId(userId).stream()
                .filter(cart -> cart.getNow() == (Boolean)true)
                .map(cart -> CartDto.builder()
                        .cartId(cart.getId())
                        .productId(cart.getProduct().getId())
                        .productName(cart.getProduct().getName())
                        .productPrice(cart.getProduct().getPrice())
                        .productThumbnail(cart.getProduct().getThumbnail())
                        .count(cart.getCount())
                        .build())
                .collect(Collectors.toList());
        return userCarts;
    }
}