package com.example.Starbucks.cart.controller;

import com.example.Starbucks.cart.dto.CartDto;
import com.example.Starbucks.cart.dto.CartUpdateDto;
import com.example.Starbucks.cart.model.Cart;
import com.example.Starbucks.cart.repository.ICartRepository;
import com.example.Starbucks.cart.service.ICartService;
import com.example.Starbucks.cart.vo.RequestCart;
import com.example.Starbucks.cart.vo.RequestSelecetDelets;
import com.example.Starbucks.cart.vo.RequestUpdateCart;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @PostMapping("")
    public ResponseEntity<Void> addCart(HttpServletRequest httpServletRequest,@RequestBody @Valid RequestCart requestCart){
        iCartService.addCart(httpServletRequest,requestCart);
        return ResponseEntity.ok().build();
        //상태만 반환
    }

    @Operation(summary = "장바구니 상품삭제", description = "장바구니 내 상품 한개 삭제", tags = { "장바구니"})
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
    @PatchMapping("/update/{id}")//수량변경
    public ResponseEntity<Void> updateCart(@PathVariable Long id, @RequestBody @Valid  RequestUpdateCart requestUpdateCart){
       iCartService.updateCart(id, requestUpdateCart);
        URI location = URI.create(String.format("/get"));
        return ResponseEntity.created(location).build();
       //유저 장바구니로 이동
    }

    @Operation(summary = "유저 장바구니목록", description = "유저 장바구니 목록 확인", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Cart.class)))
    @GetMapping("/get")
    public ResponseEntity<List<CartDto>> getByUserId(HttpServletRequest httpServletRequest){
        return ResponseEntity.ok(iCartService.getByUserId(httpServletRequest));
    }

    @Operation(summary = "장바구니 전체삭제", description = "유저가 담은 장바구니 상품 전체 삭제", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class)))
    @PutMapping("/delete")
    public ResponseEntity<Void> allDeleteCart(HttpServletRequest httpServletRequest){
        iCartService.allDeleteCart(httpServletRequest);
        URI location = URI.create(String.format("/get"));
        return ResponseEntity.created(location).build();
        //유저 장바구니로 이동
    }
    @Operation(summary = "장바구니 가져오기", description = "장바구니 수정페이지 정보 보기", tags = { "장바구니"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = CartDto.class)))
    @GetMapping("/{id}")
    public ResponseEntity<CartUpdateDto> getCart(@PathVariable Long id){
        return ResponseEntity.ok(iCartService.getCart(id));
    }

}
