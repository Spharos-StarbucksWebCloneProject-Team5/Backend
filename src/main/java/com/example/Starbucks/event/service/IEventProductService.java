package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.vo.RequestEventProduct;
import com.example.Starbucks.event.vo.ResponseAllEventProduct;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventProduct;

import java.util.List;

public interface IEventProductService {

    void addEventProduct(RequestEventProduct requestEventProduct);

    List<ResponseEvent> getByProductId(Long productId);
    //void updateEventProduct(Long id, RequestEvent requestEvent);
    void deleteEventProduct(Long id);
    List<ResponseAllEventProduct> getAllEventProduct();
    List<ResponseEventProduct> getByEventId(Long eventId);
    //void updateEventProduct(Long id, RequestEventProduct requestEventProduct);


    //List<EventProduct> getAllEventProduct();
}
