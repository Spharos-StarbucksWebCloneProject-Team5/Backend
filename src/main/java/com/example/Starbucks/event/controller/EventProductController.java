package com.example.Starbucks.event.controller;


import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.service.IEventProductService;
import com.example.Starbucks.event.vo.ResponseEventProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/event-product")
@RequiredArgsConstructor
public class EventProductController {

    final IEventProductService iEventProductService;

    @PostMapping("/add")
    public void addEventProduct(@RequestBody EventProduct eventProduct){
        iEventProductService.addEventProduct(eventProduct);
    }

    @GetMapping("/get/{eventId}")
    public List<ResponseEventProduct> getEvent(@PathVariable Long eventId){
        return iEventProductService.getByEventId(eventId);
    }
    @GetMapping("/getProduct/{productId}")
    public List<EventProduct> getProduct(@PathVariable Long productId){
        return iEventProductService.getByProductId(productId);
    }
    /*@PostMapping("/update")
    public void updateEventProduct(@RequestBody EventProduct eventProduct){
        iEventProductService.updateEventProduct(eventProduct);
    }*/

}
