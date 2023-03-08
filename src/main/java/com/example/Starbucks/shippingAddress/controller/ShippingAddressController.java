package com.example.Starbucks.shippingAddress.controller;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.service.IShippingAddressService;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/shippingAddress")
@RequiredArgsConstructor
public class ShippingAddressController {
    private final IShippingAddressService iShippingAddressService;

    @PostMapping("/add")
    public void addShippingAddress(@RequestBody ShippingAddress shippingAddress){
        iShippingAddressService.addShippingAddress(shippingAddress);
    }

    @GetMapping("/get/{shippingAddressId}")
    public ShippingAddress getShippingAddress(@PathVariable Long shippingAddressId){
        return iShippingAddressService.getShippingAddress(shippingAddressId);
    }

    @GetMapping("/get/all")
    public List<ShippingAddress> getAllShippingAddress(){
        return iShippingAddressService.getAllShippingAddress();
    }

    @PutMapping("/set/{shippingAddressId}")
    public void updateShippingAddress(@PathVariable Long shippingAddressId, @RequestBody RequestShippingAddress requestShippingAddress){
        iShippingAddressService.updateShippingAddress(shippingAddressId, requestShippingAddress);
    }

    @DeleteMapping("/del/{shippingAddressId}")
    public void deleteShippingAddress(@PathVariable Long shippingAddressId){
        iShippingAddressService.deleteShippingAddress(shippingAddressId);
    }
}
