package com.example.Starbucks.cart.service;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class CartServiceImpl implements ICartService{
    private final ICartRepository iCartRepository;
    @Override
    public void addCart(Cart cart) {
        iCartRepository.save(cart);
    }

    @Override
    public void updateCart(Cart cart) {
        Cart cart1 = iCartRepository.findById(cart.getId()).get();
        cart1.setCount(cart.getCount());
        iCartRepository.save(cart1);
    }

    @Override
    public List<Cart> getByUserId(Long userId) {
        return iCartRepository.findByUserId(userId);
    }

    @Override
    public List<Cart> getAll() {
        return iCartRepository.findAll();
    }

}
