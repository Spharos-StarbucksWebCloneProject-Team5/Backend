package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.vo.RequestEventProduct;
import com.example.Starbucks.event.vo.ResponseEventProduct;

import java.util.List;

public interface IEventProductService {

    void addEventProduct(RequestEventProduct requestEventProduct);

    List<EventProduct> getByProductId(Long productId);
    //void updateEventProduct(Long id, RequestEvent requestEvent);
    void deleteEventProduct(Long id);
    List<EventProduct> getAllEventProduct();
    List<ResponseEventProduct> getByEventId(Long eventId);

    //List<EventProduct> getAllEventProduct();
}
