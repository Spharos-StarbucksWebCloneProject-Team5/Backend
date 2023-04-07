package com.teamSiHyun.Starbucks.api.cart.controller;

import com.teamSiHyun.Starbucks.api.cart.dto.CartUpdateDto;
import com.teamSiHyun.Starbucks.api.cart.model.Cart;
import com.teamSiHyun.Starbucks.api.cart.service.ICartService;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestCart;
import com.teamSiHyun.Starbucks.api.cart.dto.CartDto;
import com.teamSiHyun.Starbucks.api.cart.repository.ICartRepository;
import com.teamSiHyun.Starbucks.api.cart.vo.RequestUpdateCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
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
    @PostMapping("")
    public ResponseEntity<?> addCart(Authentication authentication,@RequestBody @Valid RequestCart requestCart){
        return iCartService.addCart(authentication,requestCart);
        //상태만 반환
    }

    @Operation(summary = "장바구니 상품삭제", description = "장바구니 내 상품 한개 삭제", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PutMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable Long id){
        iCartService.deleteCart(id);
        return ResponseEntity.ok().build();
        //유저 장바구니로 이동
    }

    @Operation(summary = "장바구니 수량변경", description = "장바구니 상품 수량 수정", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @PatchMapping("/update/{id}")//수량변경
    public ResponseEntity<?> updateCart(@PathVariable Long id, @RequestBody @Valid  RequestUpdateCart requestUpdateCart){
        return iCartService.updateCart(id, requestUpdateCart);
       //유저 장바구니로 이동
    }

    @Operation(summary = "유저 장바구니목록", description = "유저 장바구니 목록 확인", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @GetMapping("/get")
    public ResponseEntity<List<CartDto>> getByUserId(Authentication authentication){
        return ResponseEntity.ok(iCartService.getByUserId(authentication));
    }

    @Operation(summary = "장바구니 전체삭제", description = "유저가 담은 장바구니 상품 전체 삭제", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class)))
    @PostMapping("/delete")
    public ResponseEntity<Void> allDeleteCart(Authentication authentication){
        log.info(authentication.getName());
        iCartService.allDeleteCart(authentication);
        return  ResponseEntity.ok().build();
        //유저 장바구니로 이동
    }
    @Operation(summary = "장바구니 가져오기", description = "장바구니 수정페이지 정보 보기", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class)))
    @GetMapping("/{id}")
    public ResponseEntity<CartUpdateDto> getCart(@PathVariable Long id){
        return ResponseEntity.ok(iCartService.getCart(id));
    }

}
