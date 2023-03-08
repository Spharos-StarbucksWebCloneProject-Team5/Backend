package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.repository.IEventProductRepository;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEventProduct;
import com.example.Starbucks.product.repository.IProductRepository;
import com.example.Starbucks.event.vo.ResponseEventProduct;
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
    public List<EventProduct> getByProductId(Long productId) {
        return iEventProductRepository.findByProductId(productId);
    }

    @Override
    public List<EventProduct> getAllEventProduct() {
        return iEventProductRepository.findAll();
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
                    .build();
            responseEventProductList.add(responseEventProduct);
        }
        return responseEventProductList;
    }

    /*@Override
    public List<EventProduct> getAllEventProduct() {
        return iEventProductRepository.findAll();
    }*/

    /*@Override
    public void updateEventProduct(Long id, RequestEvent requestEvent) {

        EventProduct eventProduct = iEventProductRepository.findById(id).get();
        eventProduct.set
        event.setName(requestEvent.getName());
        event.setTitle_image(requestEvent.getTitle_image());
        event.setInfo_image(requestEvent.getInfo_image());
        event.setDescription(requestEvent.getDescription());
        event.setStart_date(requestEvent.getStart_date());
        event.setEnd_date(requestEvent.getEnd_date());
        event.set_now(requestEvent.is_now());

        iEventRepository.save(event);
    }*/

    @Override
    public void deleteEventProduct(Long id) {
        EventProduct eventProduct = iEventProductRepository.findById(id).get();
        iEventProductRepository.delete(eventProduct);
    }
}
