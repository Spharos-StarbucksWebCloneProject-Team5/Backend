package com.teamSiHyun.Starbucks.api.event.service;

import com.teamSiHyun.Starbucks.api.event.vo.RequestEventProduct;
import com.teamSiHyun.Starbucks.api.event.dto.EventProductDto;

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
