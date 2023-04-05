package com.teamSiHyun.Starbucks.api.gift.service;

import com.teamSiHyun.Starbucks.api.gift.model.Gift;
import com.teamSiHyun.Starbucks.api.gift.vo.RequestCreateGift;
import com.teamSiHyun.Starbucks.api.gift.vo.ResponseGift;
import com.teamSiHyun.Starbucks.api.gift.repository.IGiftRepository;
import com.teamSiHyun.Starbucks.api.gift.vo.RequestUpdateGift;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class IGiftServiceImple implements IGiftService{

    private final IGiftRepository iGiftRepository;

    @Override
    public ResponseGift getGift(Long id) {
        Gift gift = iGiftRepository.findById(id).get();
        ResponseGift responseGift = ResponseGift.builder()
                .sendId(gift.getSendId())
                .productCount(gift.getProductCount())
                .imgUrl(gift.getImgUrl())
                .payType(gift.getPayType())
                .status(gift.getStatus())
                .build();
        return responseGift;
    }

    @Override
    public List<Gift> getAllGift() {
        return iGiftRepository.findAll();
    }

    @Override
    public List<Gift> getAllBySendId(String sendId) {
        return iGiftRepository.findAllBySendId(sendId);
    }

    @Override
    public ResponseGift addGift(RequestCreateGift requestCreateGift) {
        Gift gift = Gift.builder()
                .status(requestCreateGift.getStatus())
                .payType(requestCreateGift.getPayType())
                .imgUrl(requestCreateGift.getImgUrl())
                .sendId(requestCreateGift.getSendId())
                .productCount(requestCreateGift.getProductCount())
                .build();
        iGiftRepository.save(gift);
        ResponseGift responseGift = ResponseGift.builder()
                .status(requestCreateGift.getStatus())
                .payType(requestCreateGift.getPayType())
                .imgUrl(requestCreateGift.getImgUrl())
                .productCount(requestCreateGift.getProductCount())
                .sendId(requestCreateGift.getSendId())
                .build();
        return responseGift;
    }

    @Override
    public void updateGift(Long id, RequestUpdateGift requestGift) {
        Gift gift = iGiftRepository.findById(id).get();
        gift.setStatus(requestGift.getStatus());
        iGiftRepository.save(gift);
    }

    @Override
    public void deleteGift(Long id) {
        Gift gift = iGiftRepository.findById(id).get();
        iGiftRepository.delete(gift);
    }
}
