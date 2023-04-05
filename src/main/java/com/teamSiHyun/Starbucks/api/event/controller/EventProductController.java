package com.teamSiHyun.Starbucks.api.event.controller;


import com.teamSiHyun.Starbucks.api.event.model.EventProduct;
import com.teamSiHyun.Starbucks.api.event.service.IEventProductService;
import com.teamSiHyun.Starbucks.api.event.vo.RequestEventProduct;
import com.teamSiHyun.Starbucks.api.event.dto.EventProductDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/event-products")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventProductController { 

    final IEventProductService iEventProductService;

    @Operation(summary = "이벤트 상품등록", description = "이벤트 해당 상품 등록하기", tags = {"관리자"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventProduct.class)))
    @PostMapping("")
    public ResponseEntity<Void> addEventProduct(@RequestBody @Valid RequestEventProduct requestEventProduct){
        iEventProductService.addEventProduct(requestEventProduct);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이벤트 상품불러오기", description = "이벤트 해당 상품리스트 불러오기", tags = {"이벤트"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventProductDto.class)))
    @GetMapping("/{eventId}")
    public ResponseEntity<List<EventProductDto>>getEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(iEventProductService.getByEventId(eventId));
    }

//    @Operation(summary = "상품 해당 이벤트 조회", description = "해당 상품이 어떤 이벤트에 등록되어있는지 확인", tags = {"Event Controller"})
//    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseEvent.class)))
//    @GetMapping("/product/{productId}")//상품에 해당하는 이벤트 조회
//    public ResponseEntity<List<ResponseEvent>> getProduct(@PathVariable Long productId){
//        return ResponseEntity.ok(iEventProductService.getByProductId(productId));
//    }
//
//    @Operation(summary = "이벤트 등록상품 조회", description = "이벤트에 등록된 상품 전체 조회", tags = {"Event Controller"})
//    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = ResponseAllEventProduct.class)))
//    @GetMapping("all")
//    public ResponseEntity<List<ResponseAllEventProduct>> getEventAll(){
//        return ResponseEntity.ok(iEventProductService.getAllEventProduct());
//    }
    /*@PutMapping("{id}")
    public ResponseEntity<?> updateEventProduct(@PathVariable Long id, @RequestBody RequestEventProduct requestEventProduct) {
        iEventProductService.updateEventProduct(id, requestEventProduct);
        ResponseEntity.ok();
        return null;
    }*/

    @Operation(summary = "이벤트 상품 삭제", description = "이벤트에 등록된 상품 삭제", tags = {"관리자"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventProduct.class)))
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEventProduct(@PathVariable Long id) {
        iEventProductService.deleteEventProduct(id);
        return ResponseEntity.ok().build();
    }

}
