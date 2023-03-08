package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;

import java.util.List;

public interface IShippingAddressService {

    void addShippingAddress(ShippingAddress shippingAddress);
    ShippingAddress getShippingAddress(Long shippingId);
    List<ShippingAddress> getAllShippingAddress();
    void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress);
    void deleteShippingAddress(Long shippingId);
}
