package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.repository.IEventProductRepository;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.*;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.product.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventProductServiceImpl implements IEventProductService{

    private final IEventProductRepository iEventProductRepository;
    private final IProductRepository iProductRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addEventProduct(RequestEventProduct requestEventProduct) {
        EventProduct eventProduct = EventProduct.builder()
                .product(iProductRepository.findById(requestEventProduct.getProductId()).get())
                .event(iEventRepository.findById(requestEventProduct.getEventId()).get())
                .build();
        iEventProductRepository.save(eventProduct);

    }

    /*@Override
    public List<EventProduct> getEventProduct(Long eventProductId) {
        return iEventProductRepository.findByEventProductId(eventProductId);
    }*/
    @Override
    public List<ResponseEvent> getByProductId(Long productId) {
        List<ResponseEvent> responseEventList = iEventProductRepository.findAllByProductId(productId).stream()
                .map(element -> ResponseEvent.builder()
                        .id(element.getEvent().getId())
                        .name(element.getEvent().getName())
                        .description(element.getEvent().getDescription())
                        .titleImage(element.getEvent().getTitleImage())
                        .infoImage(element.getEvent().getInfoImage())
                        .startDate(element.getEvent().getStartDate())
                        .endDate(element.getEvent().getEndDate())
                        .now(element.getEvent().getNow())
                        .build()).collect(Collectors.toList());

        return responseEventList;
    }

    @Override
    public List<ResponseAllEventProduct> getAllEventProduct() {
        List<ResponseAllEventProduct> responseAllEventProductList = iEventProductRepository.findAll().stream()
                .map(element -> ResponseAllEventProduct.builder()
                        .id(element.getId())
                        .productId(element.getProduct().getId())
                        .eventId(element.getEvent().getId())
                        .build()).collect(Collectors.toList());

        return responseAllEventProductList;
    }

    public List<ResponseEventProduct> getByEventId(Long eventId) {
        List<ResponseEventProduct> responseEventProductList = iEventProductRepository.findAllByEventId(eventId).stream()
                .map(element -> ResponseEventProduct.builder()
                        .id(element.getId())
                        .description(element.getProduct().getDescription())
                        .name(element.getProduct().getName())
                        .price(element.getProduct().getPrice())
                        .thumbnail(element.getProduct().getThumbnail())
//                        .isShow(element.getProduct().getShow())
                        .build()).collect(Collectors.toList());

        return responseEventProductList;
    }


   /* @Override
    public void updateEventProduct(Long id, RequestEventProduct requestEventProduct) {

        EventProduct eventProduct = iEventProductRepository.findById(id).get();
        eventProduct.setEvent(requestEventProduct.getEventId());;
        eventProduct.setProduct(requestEventProduct.getProductId());
        iEventRepository.save(eventProduct);
    }*/

    @Override
    public void deleteEventProduct(Long id) {
        EventProduct eventProduct = iEventProductRepository.findById(id).get();
        iEventProductRepository.delete(eventProduct);
    }
}
