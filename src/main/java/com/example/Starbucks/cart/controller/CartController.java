package com.example.Starbucks.cart.controller;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.cart.service.ICartService;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1/api/carts")
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final ICartService iCartService;
    private final ICartRepository iCartRepository;

    @PostMapping("/add")
    public ResponseEntity<Void> addCart(@RequestBody @Valid RequestCart requestCart){
        iCartService.addCart(requestCart);
        return ResponseEntity.ok().build();
        //상태만 반환
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        iCartService.deleteCart(id);
        URI location = URI.create(String.format("/get/" + iCartRepository.findById(id).get().getUser().getId()));
        return ResponseEntity.created(location).build();
        //유저 장바구니로 이동
    }

    @PutMapping("/update")//수량변경
    public ResponseEntity<Void> updateCart(@RequestBody @Valid  RequestUpdateCart requestUpdateCart){
       iCartService.updateCart(requestUpdateCart);
        URI location = URI.create(String.format("/get/" + iCartRepository.findById(requestUpdateCart.getId()).get().getUser().getId()));
       return ResponseEntity.created(location).build();
       //유저 장바구니로 이동
    }

    @GetMapping("/get/{userId}")
    public ResponseEntity<List<CartDto>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(iCartService.getByUserId(userId));
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> allDeleteCart(@PathVariable Long userId){
        iCartService.allDeleteCart(userId);
        URI location = URI.create(String.format("/get/"+userId));
        return ResponseEntity.created(location).build();
        //유저 장바구니로 이동
    }
}
