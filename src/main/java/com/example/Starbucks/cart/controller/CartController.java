package com.example.Starbucks.cart.controller;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.cart.service.ICartService;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "장바구니 담기", description = "장바구니에 상품 추가하기", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PostMapping("/add")
    public ResponseEntity<Void> addCart(@RequestBody @Valid RequestCart requestCart){
        iCartService.addCart(requestCart);
        return ResponseEntity.ok().build();
        //상태만 반환
    }

    @Operation(summary = "장바구니 선택삭제", description = "장바구니 선택삭제", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        iCartService.deleteCart(id);
        URI location = URI.create(String.format("/get/" + iCartRepository.findById(id).get().getUser().getId()));
        return ResponseEntity.created(location).build();
        //유저 장바구니로 이동
    }

    @Operation(summary = "장바구니 수량변경", description = "장바구니 상품 수량 수정", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PutMapping("/update")//수량변경
    public ResponseEntity<Void> updateCart(@RequestBody @Valid  RequestUpdateCart requestUpdateCart){
       iCartService.updateCart(requestUpdateCart);
        URI location = URI.create(String.format("/get/" + iCartRepository.findById(requestUpdateCart.getId()).get().getUser().getId()));
       return ResponseEntity.created(location).build();
       //유저 장바구니로 이동
    }

    @Operation(summary = "유저 장바구니목록", description = "유저 장바구니 목록 확인", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PutMapping("/get/{userId}")
    public ResponseEntity<List<CartDto>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(iCartService.getByUserId(userId));
    }

    @Operation(summary = "장바구니 전체삭제", description = "장바구니 상품 전체 삭제", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class)))
    @PutMapping("/delete/{userId}")
    public ResponseEntity<Void> allDeleteCart(@PathVariable Long userId){
        iCartService.allDeleteCart(userId);
        URI location = URI.create(String.format("/get/"+userId));
        return ResponseEntity.created(location).build();
        //유저 장바구니로 이동
    }
}
