package com.example.Starbucks.event.service;

import com.example.Starbucks.category.dto.ResponseMainCategory;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService {
    private final IEventRepository iEventRepository;

    @Override
    public void addEvent(RequestEvent requestEvent) {
        ModelMapper modelMapper = new ModelMapper();
        Event event = modelMapper.map(requestEvent, Event.class);
        iEventRepository.save(event);

    }

    @Override
    public ResponseEvent getEvent(Long eventId) {
        Event event = iEventRepository.findById(eventId).get();
        ResponseEvent responseEvent = ResponseEvent.builder()
                .name(event.getName())
                .description(event.getDescription())
                .title_image(event.getTitle_image())
                .info_image(event.getInfo_image())
                .start_date(event.getStart_date())
                .end_date(event.getEnd_date())
                .isNow(event.isNow())
                .build();
        return responseEvent;

    }

    public List<ResponseEventName> getEventName() {
        List<ResponseEventName> responseEventName = iEventRepository.findAll().stream()
                .map(element -> ResponseEventName.builder()
                        .id(element.getId())
                        .name(element.getName())
                        .build()).collect(Collectors.toList());

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
    public List<ResponseEvent> getAllEvent() {
        //List<Event> eventList = iEventRepository.findAll();
        List<ResponseEvent> responseEventList = iEventRepository.findAll().stream()
                        .map(element -> ResponseEvent.builder()
                                .id(element.getId())
                                .name(element.getName())
                                .description(element.getDescription())
                                .title_image(element.getTitle_image())
                                .info_image(element.getInfo_image())
                                .start_date(element.getStart_date())
                                .end_date(element.getEnd_date())
                                .isNow(element.isNow())
                                .build()).collect(Collectors.toList());

        return responseEventList;
    }

    @Override
    public void updateEvent(Long id, RequestEvent requestEvent) {

        Event event = Event.builder()
                .id(id)
                .name(requestEvent.getName())
                .title_image(requestEvent.getTitle_image())
                .info_image(requestEvent.getInfo_image())
                .description(requestEvent.getDescription())
                .start_date(requestEvent.getStart_date())
                .end_date(requestEvent.getEnd_date())
                .isNow(requestEvent.isNow())
                .build();

        iEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = iEventRepository.findById(id).get();
        iEventRepository.delete(event);
    }
}
