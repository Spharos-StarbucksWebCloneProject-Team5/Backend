package com.teamSiHyun.Starbucks.api.shippingAddress.service;

import com.teamSiHyun.Starbucks.api.shippingAddress.vo.RequestShippingAddress;
import com.teamSiHyun.Starbucks.api.shippingAddress.vo.ResponseShippingAddress;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface IShippingAddressService {

    void addShippingAddress(Authentication authentication, RequestShippingAddress requestShippingAddress);
    ResponseShippingAddress getShippingAddress(Long shippingId);
    List<ResponseShippingAddress> getAllShippingAddress(Authentication authentication);
    void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress);
    void deleteShippingAddress(Long shippingId);
    ResponseShippingAddress getMainShippingAddress(Authentication authentication);
}
