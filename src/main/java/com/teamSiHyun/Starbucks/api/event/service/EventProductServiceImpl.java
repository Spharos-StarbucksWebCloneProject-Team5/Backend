package com.teamSiHyun.Starbucks.api.event.service;

import com.teamSiHyun.Starbucks.api.event.model.EventProduct;
import com.teamSiHyun.Starbucks.api.event.vo.RequestEventProduct;
import com.teamSiHyun.Starbucks.api.product.repository.IProductRepository;
import com.teamSiHyun.Starbucks.api.event.dto.EventProductDto;
import com.teamSiHyun.Starbucks.api.event.repository.IEventProductRepository;
import com.teamSiHyun.Starbucks.api.event.repository.IEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
//    @Override
//    public List<ResponseEvent> getByProductId(Long productId) {
//        List<ResponseEvent> responseEventList = iEventProductRepository.findAllByProductId(productId).stream()
//                .map(element -> ResponseEvent.builder()
//                        .id(element.getEvent().getId())
//                        .name(element.getEvent().getName())
//                        .description(element.getEvent().getDescription())
//                        .titleImage(element.getEvent().getTitleImage())
//                        .infoImage(element.getEvent().getInfoImage())
//                        .startDate(element.getEvent().getStartDate())
//                        .endDate(element.getEvent().getEndDate())
////                        .now(element.getEvent().getNow())
//                        .build()).collect(Collectors.toList());
//
//        return responseEventList;
//    }

    @Override
    public List<EventProductDto> getAllEventProduct() {
        List<EventProductDto> eventProductDtoAllList = iEventProductRepository.findAll().stream()
                .map(element -> EventProductDto.builder()
                .id(element.getId())
                .description(element.getProduct().getDescription())
                .name(element.getProduct().getName())
                .price(element.getProduct().getPrice())
                .thumbnail(element.getProduct().getThumbnail())
                .build()).collect(Collectors.toList());

        return eventProductDtoAllList;
    }

    public List<EventProductDto> getByEventId(Long eventId) {
        List<EventProductDto> eventProductDtoList = iEventProductRepository.findAllByEventId(eventId).stream()
                .map(element -> EventProductDto.builder()
                        .id(element.getId())
                        .productId(element.getProduct().getId())
                        .description(element.getProduct().getDescription())
                        .name(element.getProduct().getName())
                        .price(element.getProduct().getPrice())
                        .thumbnail(element.getProduct().getThumbnail())
                        .build()).collect(Collectors.toList());

        return eventProductDtoList ;
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
