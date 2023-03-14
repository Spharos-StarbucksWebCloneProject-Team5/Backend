package com.example.Starbucks.event.service;


import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.repository.IEventImageListRepository;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.RequestEventImageList;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventImageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventImageListServiceImpl implements IEventImageListService {

    private final IEventImageListRepository iEventImageListRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addEventImage(RequestEventImageList requestEventImageList) {
        EventImageList eventImageList = EventImageList.builder()
                .event(iEventRepository.findById(requestEventImageList.getEventId()).get())
                .image(requestEventImageList.getImage())
                .build();

        iEventImageListRepository.save(eventImageList);
    }
    @Override
    public List<ResponseEventImageList> getByEventId(Long eventId) {
        List<ResponseEventImageList> responseEventImageList
                = iEventImageListRepository.findAllByEventId(eventId).stream()
                        .map(element -> ResponseEventImageList.builder()
                                .id(element.getId())
                                .eventId(element.getEvent().getId())
                                .image(element.getImage())
                                .build()).collect(Collectors.toList());

        return responseEventImageList;
    }

    @Override
    public List<ResponseEventImageList> getAll() {
        //List<EventImageList> eventImageList = iEventImageListRepository.findAll();
        List<ResponseEventImageList> responseEventImageList = iEventImageListRepository.findAll().stream()
                .map(element -> ResponseEventImageList.builder()
                        .id(element.getId())
                        .eventId(element.getEvent().getId())
                        .image(element.getImage())
                        .build()).collect(Collectors.toList());

        return responseEventImageList;
    }

    @Override
    public void updateEventImageList(Long id, RequestEventImageList requestEventImageList){

        EventImageList eventImageList = EventImageList.builder()
                .id(id)
                .image(requestEventImageList.getImage())
                .build();

        iEventImageListRepository.save(eventImageList);
    }

    @Override
    public void deleteEventImageList(Long id) {
        EventImageList eventImageList = iEventImageListRepository.findById(id).get();
        iEventImageListRepository.delete(eventImageList);
    }
}
