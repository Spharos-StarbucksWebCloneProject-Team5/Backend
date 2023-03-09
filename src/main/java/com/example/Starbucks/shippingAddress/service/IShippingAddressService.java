package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import com.example.Starbucks.shippingAddress.vo.ResponseShippingAddress;

import java.util.List;

public interface IShippingAddressService {

    void addShippingAddress(ShippingAddress shippingAddress);
    ResponseShippingAddress getShippingAddress(Long shippingId);
    List<ResponseShippingAddress> getAllShippingAddress();
    void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress);
    void deleteShippingAddress(Long shippingId);
}
