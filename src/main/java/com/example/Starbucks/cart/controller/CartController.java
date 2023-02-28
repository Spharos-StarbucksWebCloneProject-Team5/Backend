package com.example.Starbucks.cart.controller;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.service.ICartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/cart")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private ICartService iCartService;

    @PostMapping("/add")
    public void addCart(@RequestBody Cart cart){
        iCartService.addCart(cart);
    }

   /*@DeleteMapping("/delete")
    public void deleteCart(@RequestBody Cart cart){ iCartService.deleteCart(cart);}

    @PostMapping("/updateCount")//post가 맞나? patch인가?
    public void updateCartCount(@RequestBody Cart cart){ iCartService.updateCart(cart);}*/

    @PostMapping("/update")//수량변경
    public void updateCart(@RequestBody Cart cart){ iCartService.updateCart(cart);}

    @GetMapping("/get/{userId}")
    public List<Cart> getCart(@PathVariable Long userId){
        return iCartService.getByUserId(userId);
    }

    @GetMapping("/get/all")
    public List<Cart> getAllCart(){
        return iCartService.getAll();
    }


}
