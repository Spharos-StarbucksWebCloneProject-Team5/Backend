package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import com.example.Starbucks.cart.vo.ResponseUserCart;
import com.example.Starbucks.category.model.CategoryList;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.users.repository.UserRepository;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.implementation.bytecode.collection.ArrayLength;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CartServiceImpl implements ICartService{
    private final ICartRepository iCartRepository;
    private final UserRepository userRepository;
    private final IProductRepository iProductRepository;

    @Override
    public void addCart(RequestCart requestCart) {
        //
        boolean pc = iCartRepository.existsByUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
        Cart cart;
        if(pc){
            cart = iCartRepository.findByUserIdAndProductId(requestCart.getUserId(), requestCart.getProductId());
            cart.setCount(cart.getCount() + requestCart.getCount());
        }
        else{
            cart = Cart.builder()
                    .user(userRepository.findById(requestCart.getUserId()).get())
                    .product(iProductRepository.findById(requestCart.getProductId()).get())
                    .count(requestCart.getCount())
                    .build();
        }
        cart.setCancel(true);
        iCartRepository.save(cart);
    }

    @Override
    public void updateCart(RequestUpdateCart requestUpdateCart){
        Cart cart = iCartRepository.findById(requestUpdateCart.getId()).get();
        cart.setCount(requestUpdateCart.getCount());
        iCartRepository.save(cart);
    }

    @Override
    public void deleteCart(Long id) {
        Cart cart = iCartRepository.findById(id).get();
        cart.setCount(0);
        cart.setCancel(false);
        iCartRepository.save(cart);
    }
    @Override
    public void allDeleteCart(Long userId) {
        List<Cart> carts = iCartRepository.findAllByUserId(userId);
        for(Cart cart : carts){
            cart.setCount(0);
            cart.setCancel(false);
            iCartRepository.save(cart);
        }
    }

    @Override
    public List<ResponseUserCart> getByUserId(Long userId) {
        List<Cart> carts = iCartRepository.findAllByUserId(userId);
        List<ResponseUserCart> userCarts = new ArrayList<>();
        for (Cart cart : carts){
            ResponseUserCart responseUserCart = ResponseUserCart.builder()
                    .productId(cart.getProduct().getId())
                    .productName(cart.getProduct().getName())
                    .productPrice(cart.getProduct().getPrice())
                    .productThumbnail(cart.getProduct().getThumbnail())
                    .count(cart.getCount())
                    .build();
            userCarts.add(responseUserCart);
        }
        return userCarts;
    }

//    @Override
//    public List<Cart> getAll() {
//        return iCartRepository.findAll();
//    }
}
