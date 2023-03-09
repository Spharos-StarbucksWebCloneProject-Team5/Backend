package com.example.Starbucks.shippingAddress.controller;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.service.IShippingAddressService;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import com.example.Starbucks.shippingAddress.vo.ResponseShippingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/shippingAddress")
@RequiredArgsConstructor
public class ShippingAddressController {
    private final IShippingAddressService iShippingAddressService;

    @PostMapping("")
    public ResponseEntity<?> addShippingAddress(@RequestBody ShippingAddress shippingAddress){
        iShippingAddressService.addShippingAddress(shippingAddress);
        ResponseEntity.ok();
        return null;
    }

    @GetMapping("/{shippingAddressId}")
    public ResponseEntity<ResponseShippingAddress> getShippingAddress(@PathVariable Long shippingAddressId){
        return ResponseEntity.ok(iShippingAddressService.getShippingAddress(shippingAddressId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseShippingAddress>> getAllShippingAddress(){
        return ResponseEntity.ok(iShippingAddressService.getAllShippingAddress());
    }

    @PutMapping("{shippingAddressId}")
    public ResponseEntity<?> updateShippingAddress(@PathVariable Long shippingAddressId, @RequestBody RequestShippingAddress requestShippingAddress){
        iShippingAddressService.updateShippingAddress(shippingAddressId, requestShippingAddress);
        ResponseEntity.ok();
        return null;
    }

    @DeleteMapping("{shippingAddressId}")
    public ResponseEntity<?> deleteShippingAddress(@PathVariable Long shippingAddressId){
        iShippingAddressService.deleteShippingAddress(shippingAddressId);
        ResponseEntity.ok();
        return null;
    }
}
