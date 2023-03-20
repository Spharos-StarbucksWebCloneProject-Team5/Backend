package com.example.Starbucks.event.controller;


import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.service.IEventProductService;
import com.example.Starbucks.event.vo.*;
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

    @PostMapping("")
    public ResponseEntity addEventProduct(@RequestBody @Valid RequestEventProduct requestEventProduct){
        iEventProductService.addEventProduct(requestEventProduct);
        return null;
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<List<ResponseEventProduct>>getEvent(@PathVariable Long eventId){
        return ResponseEntity.ok(iEventProductService.getByEventId(eventId));
    }
    @GetMapping("/product/{productId}")//상품에 해당하는 이벤트 조회
    public ResponseEntity<List<ResponseEvent>> getProduct(@PathVariable Long productId){
        return ResponseEntity.ok(iEventProductService.getByProductId(productId));
    }
    @GetMapping("all")
    public ResponseEntity<List<ResponseAllEventProduct>> getEventAll(){
        return ResponseEntity.ok(iEventProductService.getAllEventProduct());
    }
    /*@PutMapping("{id}")
    public ResponseEntity<?> updateEventProduct(@PathVariable Long id, @RequestBody RequestEventProduct requestEventProduct) {
        iEventProductService.updateEventProduct(id, requestEventProduct);
        ResponseEntity.ok();
        return null;
    }*/

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEventProduct(@PathVariable Long id) {
        iEventProductService.deleteEventProduct(id);
        return null;
    }

}
