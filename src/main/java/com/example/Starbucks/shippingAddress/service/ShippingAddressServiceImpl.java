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
        if((Boolean)shippingAddress.getChoiceMain()){
            iShippingAddressRepository.mainChange(shippingAddress.getUserId());
        }
        iShippingAddressRepository.save(shippingAddress);
    }

    @Override
    public ResponseShippingAddress getShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        ResponseShippingAddress responseShippingAddress = ResponseShippingAddress.builder()
                .id(shippingAddress.getId())
                .address(shippingAddress.getAddress())
                .choiceMain(shippingAddress.getChoiceMain())
                .build();

        return responseShippingAddress;
    }

    @Override
    public List<ResponseShippingAddress> getAllShippingAddress(Long userId) {
        List<ShippingAddress> shippingAddressesList = iShippingAddressRepository.findAllByUserId(userId);
        List<ResponseShippingAddress> responseShippingAddresses = new ArrayList<>();
        for(int i=0;i<shippingAddressesList.size();i++){
            responseShippingAddresses.add(ResponseShippingAddress.builder()
                    .id(shippingAddressesList.get(i).getId())
                    .userId(shippingAddressesList.get(i).getId())
                    .nickname(shippingAddressesList.get(i).getNickname())
                    .receiver(shippingAddressesList.get(i).getReceiver())
                    .zipCode(shippingAddressesList.get(i).getZipCode())
                    .address(shippingAddressesList.get(i).getAddress())
                    .detailAddress(shippingAddressesList.get(i).getDetailAddress())
                    .shippingPhone1(shippingAddressesList.get(i).getShippingPhone1())
                    .shippingPhone2(shippingAddressesList.get(i).getShippingPhone2())
                    .shippingMemo(shippingAddressesList.get(i).getShippingMemo())
                    .choiceMain(shippingAddressesList.get(i).getChoiceMain())
                    .build()
            );
        }

        return responseShippingAddresses;
    }

    @Override
    public void updateShippingAddress(Long shippingId, RequestShippingAddress requestShippingAddress) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        if((Boolean)requestShippingAddress.getChoiceMain()){
            iShippingAddressRepository.mainChange(shippingAddress.getUserId());
        }
        iShippingAddressRepository.save(ShippingAddress.builder()
                .id(shippingAddress.getId())
                .userId(shippingAddress.getUserId())
                .nickname(requestShippingAddress.getNickname())
                .receiver(requestShippingAddress.getReceiver())
                .zipCode(requestShippingAddress.getZipCode())
                .address(requestShippingAddress.getAddress())
                .detailAddress(requestShippingAddress.getDetailAddress())
                .shippingPhone1(requestShippingAddress.getShippingPhone1())
                .shippingPhone2(requestShippingAddress.getShippingPhone2())
                .shippingMemo(requestShippingAddress.getShippingMemo())
                .choiceMain(requestShippingAddress.getChoiceMain())
                .build());
    }

    @Override
    public void deleteShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        iShippingAddressRepository.delete(shippingAddress);
    }
}
