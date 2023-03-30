package com.example.Starbucks.shippingAddress.service;

import com.example.Starbucks.jwt.JwtTokenProvider;
import com.example.Starbucks.shippingAddress.model.ShippingAddress;
import com.example.Starbucks.shippingAddress.repository.IShippingAddressRepository;
import com.example.Starbucks.shippingAddress.vo.RequestShippingAddress;
import com.example.Starbucks.shippingAddress.vo.ResponseShippingAddress;
import com.example.Starbucks.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShippingAddressServiceImpl implements IShippingAddressService{

    private final IShippingAddressRepository iShippingAddressRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    @Override
    public void addShippingAddress(Authentication authentication, RequestShippingAddress requestShippingAddress) {
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
        if((Boolean)requestShippingAddress.getChoiceMain()){
            iShippingAddressRepository.mainChange(userId);
        }
        iShippingAddressRepository.save(ShippingAddress.builder()
                        .userId(userId)
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
    public ResponseShippingAddress getShippingAddress(Long shippingId) {
        ShippingAddress shippingAddress = iShippingAddressRepository.findById(shippingId).get();
        ResponseShippingAddress responseShippingAddress = ResponseShippingAddress.builder()
                .id(shippingAddress.getId())
                .nickname(shippingAddress.getNickname())
                .receiver(shippingAddress.getReceiver())
                .zipCode(shippingAddress.getZipCode())
                .address(shippingAddress.getAddress())
                .detailAddress(shippingAddress.getDetailAddress())
                .shippingPhone1(shippingAddress.getShippingPhone1())
                .shippingPhone2(shippingAddress.getShippingPhone2())
                .shippingMemo(shippingAddress.getShippingMemo())
                .choiceMain(shippingAddress.getChoiceMain())
                .build();

        return responseShippingAddress;
    }

    @Override
    public List<ResponseShippingAddress> getAllShippingAddress(Authentication authentication) {
        Long userId = userRepository.findByEmail(authentication.getName()).get().getId();
        List<ResponseShippingAddress> responseShippingAddresses = iShippingAddressRepository.findAllByUserId(userId).stream()
                .map(shippingAddress -> ResponseShippingAddress.builder()
                        .id(shippingAddress.getId())
                        .nickname(shippingAddress.getNickname())
                        .receiver(shippingAddress.getReceiver())
                        .zipCode(shippingAddress.getZipCode())
                        .address(shippingAddress.getAddress())
                        .detailAddress(shippingAddress.getDetailAddress())
                        .shippingPhone1(shippingAddress.getShippingPhone1())
                        .shippingPhone2(shippingAddress.getShippingPhone2())
                        .shippingMemo(shippingAddress.getShippingMemo())
                        .choiceMain(shippingAddress.getChoiceMain())
                        .build())
                .collect(Collectors.toList());
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
