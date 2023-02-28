package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventProduct;

import java.util.List;

public interface IEventProductService {

    void addEventProduct(EventProduct eventProduct);

    List<EventProduct> getByProductId(Long productId);
    List<EventProduct> getByEventId(Long eventId);

    //List<EventProduct> getAllEventProduct();
}
