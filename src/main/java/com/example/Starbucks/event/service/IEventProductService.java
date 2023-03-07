package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.vo.RequestEventProduct;

import java.util.List;

public interface IEventProductService {

    void addEventProduct(RequestEventProduct requestEventProduct);

    List<EventProduct> getByProductId(Long productId);
    List<EventProduct> getByEventId(Long eventId);
    //void updateEventProduct(Long id, RequestEvent requestEvent);
    void deleteEventProduct(Long id);
    List<EventProduct> getAllEventProduct();

    //List<EventProduct> getAllEventProduct();
}
