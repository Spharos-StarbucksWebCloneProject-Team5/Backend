package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.repository.IShippingAddressRepository;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements IShippingAddressService{

    private final IShippingAddressRepository iShippingAddressRepository;
    @Override
    public void addShippingAddress(ShippingAddress shippingAddress) {
        iShippingAddressRepository.save(shippingAddress);
    }

    @Override
    public ShippingAddress getShippingAddress(Long shippingId) {
        return iShippingAddressRepository.findById(shippingId).get();
    }

    @Override
    public List<ShippingAddress> getAllShippingAddress() {
        return iShippingAddressRepository.findAll();
    }

    @Override
    public void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        shippingAddress.setAddress(requestShippingAddress.getAddress());
        shippingAddress.setIsMain(requestShippingAddress.getIsMain());
        iShippingAddressRepository.save(shippingAddress);
    }

    @Override
    public void deleteShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        iShippingAddressRepository.delete(shippingAddress);
    }
}
