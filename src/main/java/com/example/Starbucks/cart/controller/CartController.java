package com.example.Starbucks.cart.controller;

import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.service.ICartService;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import com.example.Starbucks.cart.vo.ResponseUserCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final ICartService iCartService;

    @PostMapping("/add")
    public void addCart(@RequestBody RequestCart requestCart){
        iCartService.addCart(requestCart);
    }

    @DeleteMapping("/{id}")
    public void deleteCart(@PathVariable Long id){
        iCartService.deleteCart(id);
    }

    @PutMapping("/update")//수량변경
    public void updateCart(@RequestBody RequestUpdateCart requestUpdateCart){
       iCartService.updateCart(requestUpdateCart);
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<ResponseUserCart>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(iCartService.getByUserId(userId));
    }

    @DeleteMapping("/delete/{userId}")
    public void allDeleteCart(@PathVariable Long userId){
        iCartService.allDeleteCart(userId);
    }
}
