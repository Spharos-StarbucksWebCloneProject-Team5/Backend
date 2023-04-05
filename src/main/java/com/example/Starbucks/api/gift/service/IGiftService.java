package com.example.Starbucks.api.gift.service;

import com.example.Starbucks.api.gift.model.Gift;
import com.example.Starbucks.api.gift.vo.RequestCreateGift;
import com.example.Starbucks.api.gift.vo.ResponseGift;
import com.example.Starbucks.api.gift.vo.RequestUpdateGift;

import java.util.List;

public interface IGiftService {
    ResponseGift getGift(Long id);
    List<Gift> getAllGift();
    List<Gift> getAllBySendId(String sendId);
    ResponseGift addGift(RequestCreateGift requestCreateGift);
    void updateGift(Long id, RequestUpdateGift requestGIft);

    void deleteGift(Long id);
}
