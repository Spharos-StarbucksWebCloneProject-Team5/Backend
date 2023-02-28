package com.example.Starbucks.gift.service;

import com.example.Starbucks.gift.model.Gift;
import com.example.Starbucks.gift.vo.RequestCreateGift;
import com.example.Starbucks.gift.vo.RequestUpdateGift;
import com.example.Starbucks.gift.vo.ResponseGift;

import java.util.List;

public interface IGiftService {
    ResponseGift getGift(Long id);
    List<Gift> getAllGift();
    List<Gift> getAllBySendId(String sendId);
    ResponseGift addGift(RequestCreateGift requestCreateGift);
    void updateGift(Long id, RequestUpdateGift requestGIft);

    void deleteGift(Long id);
}
