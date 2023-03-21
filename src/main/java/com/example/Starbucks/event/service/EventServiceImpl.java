package com.example.Starbucks.event.service;

import com.example.Starbucks.category.dto.ResponseMainCategory;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventList;
import com.example.Starbucks.event.vo.ResponseEventName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
    private final IEventRepository iEventRepository;

    @Override
    public void addEvent(RequestEvent requestEvent) {

        Event event = Event.builder()
                .name(requestEvent.getName())
                .description(requestEvent.getDescription())
                .titleImage(requestEvent.getTitleImage())
                .infoImage(requestEvent.getInfoImage())
                .startDate(requestEvent.getStartDate())
                .endDate(requestEvent.getEndDate())
                .now(requestEvent.getNow())
                .build();
        //log.info("isNow 값 : "+requestEvent.toString());
        iEventRepository.save(event);

    }

    @Override
    public ResponseEvent getEvent(Long eventId) {
        Event event = iEventRepository.findById(eventId).get();
        ResponseEvent responseEvent = ResponseEvent.builder()
                .id(eventId)
                .name(event.getName())
                .description(event.getDescription())
                .titleImage(event.getTitleImage())
                .infoImage(event.getInfoImage())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .now(event.getNow())
                .build();
        return responseEvent;

    }

    public List<ResponseEventName> getEventName() {
        List<ResponseEventName> responseEventName = iEventRepository.findAll().stream()
                .map(element -> ResponseEventName.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .build()).collect(Collectors.toList());

        log.info(responseEventName.toString());
        /*for (Event event : events) {
            if (event.isNow()) {
                responseEventName.add(ResponseEventName.builder()
                        .id(event.getId())
                        .name(event.getName())
                        .build());
            }
        }*/
        return responseEventName;
    }

    @Override
    public List<ResponseEventList> getAllEvent() {
        //List<Event> eventList = iEventRepository.findAll();
        AtomicLong idx = new AtomicLong(1L);
        List<ResponseEventList> responseEventList = iEventRepository.findAllByNow(Boolean.TRUE).stream()
                        .map(element -> ResponseEventList.builder()
                                .index(idx.getAndIncrement())       //이벤트 index(순서)
                                .eventId(element.getId())
                                .description(element.getDescription())
                                .build()).collect(Collectors.toList());

        return responseEventList;
    }

    @Override
    public void updateEvent(Long id, RequestEvent requestEvent) {

        Event event = Event.builder()
                .id(id)
                .name(requestEvent.getName())
                .titleImage(requestEvent.getTitleImage())
                .infoImage(requestEvent.getInfoImage())
                .description(requestEvent.getDescription())
                .startDate(requestEvent.getStartDate())
                .endDate(requestEvent.getEndDate())
                .now(requestEvent.getNow())
                .build();

        iEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = iEventRepository.findById(id).get();
        iEventRepository.delete(event);
    }
}
