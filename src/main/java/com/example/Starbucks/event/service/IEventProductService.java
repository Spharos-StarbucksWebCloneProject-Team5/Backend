package com.example.Starbucks.event.service;

import com.example.Starbucks.event.dto.EventProductDto;
import com.example.Starbucks.event.vo.RequestEventProduct;

import java.util.List;

public interface IEventProductService {

    void addEventProduct(RequestEventProduct requestEventProduct);

//    List<ResponseEvent> getByProductId(Long productId);
    //void updateEventProduct(Long id, RequestEvent requestEvent);
    void deleteEventProduct(Long id);
    List<EventProductDto> getAllEventProduct();
    List<EventProductDto> getByEventId(Long eventId);
    //void updateEventProduct(Long id, RequestEventProduct requestEventProduct);


    //List<EventProduct> getAllEventProduct();
}
