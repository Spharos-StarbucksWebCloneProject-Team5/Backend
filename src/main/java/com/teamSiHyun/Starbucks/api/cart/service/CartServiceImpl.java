package com.teamSiHyun.Starbucks.api.cart.service;

import com.teamSiHyun.Starbucks.api.cart.dto.CartUpdateDto;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestCart;
import com.teamSiHyun.Starbucks.api.category.repository.CategoryListRepository;
import com.teamSiHyun.Starbucks.api.product.ResponseInventory;
import com.teamSiHyun.Starbucks.api.product.repository.IProductRepository;
import com.teamSiHyun.Starbucks.api.cart.dto.CartDto;
import com.teamSiHyun.Starbucks.api.cart.model.Cart;
import com.teamSiHyun.Starbucks.api.cart.repository.ICartRepository;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestUpdateCart;
import com.teamSiHyun.Starbucks.jwt.JwtTokenProvider;
import com.teamSiHyun.Starbucks.api.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    private final ResponseInventory responseInventory;
    private final JwtTokenProvider jwtTokenProvider;
    private final CategoryListRepository categoryListRepository;

    @Override
    public ResponseEntity<?> addCart(Authentication authentication, RequestCart requestCart) {
        log.info(requestCart.toString());
        if(requestCart.getCount() > iProductRepository.findById(requestCart.getProductId()).get().getCount()){
            return responseInventory.cartFail(iProductRepository.findById(requestCart.getProductId()).get().getCount());
        }
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
        //장바구니에 담았던 상품일 시 수량 추가
        Optional<Cart> cart = iCartRepository.findByUserIdAndProductId(userId, requestCart.getProductId());
        log.info("{}",cart.isPresent());
        if (cart.isPresent()){
            cart.get().setUpdateCount(cart.get().getCount() + requestCart.getCount());
            iCartRepository.save(cart.get());
            return responseInventory.cartSuccess();
        } else {
            iCartRepository.save(Cart.builder()
                    .user(userRepository.findById(userId).get())
                    .product(iProductRepository.findById(requestCart.getProductId()).get())
                    .count(requestCart.getCount())
                    .now(true)
                    .build());
            return responseInventory.cartSuccess();
        }
//        return null;
    }

    @Override
    public ResponseEntity<?> updateCart(Long id, RequestUpdateCart requestUpdateCart){
        Cart cart = iCartRepository.findById(id).get();
        if(requestUpdateCart.getCount() > cart.getProduct().getCount()){
            return responseInventory.cartFail(cart.getProduct().getCount());
        }
        cart.setUpdateCount(requestUpdateCart.getCount());
        iCartRepository.save(cart);
        return responseInventory.cartSuccess();
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
    public void allDeleteCart(Authentication authentication) {
        List<Cart> carts = iCartRepository.findAllByUserId(userRepository.findByEmail(authentication.getName()).get().getId());
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
    public List<CartDto> getByUserId(Authentication authentication) {
        List<CartDto> userCarts = iCartRepository.findAllByUserId(userRepository.findByEmail(authentication.getName()).get().getId()).stream()
                .filter(cart -> cart.getNow() == (Boolean)true)
                .map(cart -> CartDto.builder()
                        .cartId(cart.getId())
                        .mainCategoryId(categoryListRepository.findByProductId(cart.getProduct().getId()).getMainCategory().getId())
                        .productId(cart.getProduct().getId())
                        .productName(cart.getProduct().getName())
                        .productPrice(cart.getProduct().getPrice())
                        .productThumbnail(cart.getProduct().getThumbnail())
                        .productInventory(cart.getProduct().getCount())
                        .count(cart.getCount())
                        .build())
                .collect(Collectors.toList());
        return userCarts;
    }

    @Override
    public CartUpdateDto getCart(Long id) {
        Cart cart = iCartRepository.findById(id).get();
        return CartUpdateDto.builder()
                .productId(cart.getProduct().getId())
                .productName(cart.getProduct().getName())
                .productPrice(cart.getProduct().getPrice())
                .productThumbnail(cart.getProduct().getThumbnail())
                .count(cart.getCount())
                .build();
    }


}