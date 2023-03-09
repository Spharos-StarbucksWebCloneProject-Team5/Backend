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
        List<EventProduct> eventProductList = iEventProductRepository.findAllByProductId(productId);
        List<ResponseEvent> responseEventList = new ArrayList<>();

        for(int i=0; i<eventProductList.size(); i++){
            responseEventList.add(ResponseEvent.builder()
                    .id(eventProductList.get(i).getEvent().getId())
                    .name(eventProductList.get(i).getEvent().getName())
                    .description(eventProductList.get(i).getEvent().getDescription())
                    .title_image(eventProductList.get(i).getEvent().getTitle_image())
                    .info_image(eventProductList.get(i).getEvent().getInfo_image())
                    .start_date(eventProductList.get(i).getEvent().getStart_date())
                    .end_date(eventProductList.get(i).getEvent().getEnd_date())
                    .isNow(eventProductList.get(i).getEvent().isNow())
                    .build());
        }
        return responseEventList;
    }

    @Override
    public List<ResponseAllEventProduct> getAllEventProduct() {
        List<EventProduct> eventProductList = iEventProductRepository.findAll();
        List<ResponseAllEventProduct> responseAllEventProductList = new ArrayList<>();
        for (EventProduct eventProduct : eventProductList) {
            responseAllEventProductList.add(ResponseAllEventProduct.builder()
                    .id(eventProduct.getId())
                    .productId(eventProduct.getProduct().getId())
                    .eventId(eventProduct.getEvent().getId())
                    .build());
        }
        return responseAllEventProductList;
    }

    public List<ResponseEventProduct> getByEventId(Long eventId) {
        List<EventProduct> eventProductList = iEventProductRepository.findAllByEventId(eventId);
        List<ResponseEventProduct> responseEventProductList = new ArrayList<>();
        for (EventProduct eventProduct : eventProductList) {
            Product product = eventProduct.getProduct();
            ResponseEventProduct responseEventProduct = ResponseEventProduct.builder()
                    .id(product.getId())
                    .description(product.getDescription())
                    .name(product.getName())
                    .price(product.getPrice())
                    .thumbnail(product.getThumbnail())
                    .isShow(product.isShow())
                    .build();
            responseEventProductList.add(responseEventProduct);
        }
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
