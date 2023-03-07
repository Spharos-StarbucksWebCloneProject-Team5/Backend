package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.repository.IEventProductRepository;
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

    @Override
    public void addEventProduct(EventProduct eventProduct) {
        iEventProductRepository.save(eventProduct);
    }

    /*@Override
    public List<EventProduct> getEventProduct(Long eventProductId) {
        return iEventProductRepository.findByEventProductId(eventProductId);
    }*/
    @Override
    public List<EventProduct> getByProductId(Long productId) {
        return iEventProductRepository.findAllByProductId(productId);
    }

    @Override
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
    public void updateEventProduct(EventProduct eventProduct){

        EventProduct eventProduct1 = iEventProductRepository.findById(eventProduct.getId()).get();
        eventProduct1.setImage(eventImageList.getImage());
        //eventImageList1.set(eventImageList.getUpdateDate());


        iEventProductRepository.save(eventProduct1);
    }*/
}
