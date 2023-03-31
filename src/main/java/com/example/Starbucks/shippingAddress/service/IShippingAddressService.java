package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import com.example.Starbucks.shippingAddress.vo.ResponseShippingAddress;
import org.springframework.security.core.Authentication;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IShippingAddressService {

    void addShippingAddress(Authentication authentication, RequestShippingAddress requestShippingAddress);
    ResponseShippingAddress getShippingAddress(Long shippingId);
    List<ResponseShippingAddress> getAllShippingAddress(Authentication authentication);
    void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress);
    void deleteShippingAddress(Long shippingId);
}
