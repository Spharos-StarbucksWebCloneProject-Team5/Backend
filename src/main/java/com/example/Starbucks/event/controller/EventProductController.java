package com.example.Starbucks.event.controller;


import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.service.IEventProductService;
import com.example.Starbucks.event.vo.RequestEventProduct;
import com.example.Starbucks.event.vo.ResponseEventProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/event-products")
@RequiredArgsConstructor
public class EventProductController {

    final IEventProductService iEventProductService;

    @PostMapping("")
    public void addEventProduct(@RequestBody RequestEventProduct requestEventProduct){
        iEventProductService.addEventProduct(requestEventProduct);
    }

    @GetMapping("/{eventId}")
    public List<ResponseEventProduct> getEvent(@PathVariable Long eventId){
        return iEventProductService.getByEventId(eventId);
    }
    @GetMapping("/product/{productId}")//상품에 해당하는 이벤트 조회
    public List<EventProduct> getProduct(@PathVariable Long productId){
        return iEventProductService.getByProductId(productId);
    }
    @GetMapping("all")//이벤트에 해당하는 상품 조회
    public List<EventProduct> getEvent(){
        return iEventProductService.getAllEventProduct();
    }


    @DeleteMapping("{id}")
    public void deleteEventProduct(@PathVariable Long id) {
        iEventProductService.deleteEventProduct(id);
    }

}
