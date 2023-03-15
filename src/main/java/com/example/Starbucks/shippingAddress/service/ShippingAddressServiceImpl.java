package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.repository.IShippingAddressRepository;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import com.example.Starbucks.shippingAddress.vo.ResponseShippingAddress;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ResponseShippingAddress getShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        ResponseShippingAddress responseShippingAddress = ResponseShippingAddress.builder()
                .id(shippingAddress.getId())
                .address(shippingAddress.getAddress())
                .main(shippingAddress.getMain())
                .choice(shippingAddress.getChoice())
                .build();

        return responseShippingAddress;
    }

    @Override
    public List<ResponseShippingAddress> getAllShippingAddress() {
        List<ShippingAddress> shippingAddressesList = iShippingAddressRepository.findAll();
        List<ResponseShippingAddress> responseShippingAddresses = new ArrayList<>();
        for(int i=0;i<shippingAddressesList.size();i++){
            responseShippingAddresses.add(ResponseShippingAddress.builder()
                    .id(shippingAddressesList.get(i).getId())
                    .address(shippingAddressesList.get(i).getAddress())
                    .main(shippingAddressesList.get(i).getMain())
                    .choice(shippingAddressesList.get(i).getChoice())
                    .build()
            );
        }

        return responseShippingAddresses;
    }

    @Override
    public void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        shippingAddress.setAddress(requestShippingAddress.getAddress());
        shippingAddress.setMain(requestShippingAddress.getMain());
        shippingAddress.setChoice(requestShippingAddress.getChoice());

        iShippingAddressRepository.save(shippingAddress);
    }

    @Override
    public void deleteShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        iShippingAddressRepository.delete(shippingAddress);
    }
}
