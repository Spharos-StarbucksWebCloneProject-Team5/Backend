package com.example.Starbucks.event.service;

import com.example.Starbucks.event.dto.EventDto;
import com.example.Starbucks.event.dto.EventListDto;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public List<EventDto> getEventImage() {
        AtomicLong idx = new AtomicLong(1L);
        List<EventDto>  eventDtoList = iEventRepository.findAllByNow(Boolean.TRUE).stream()
                .map(event -> EventDto.builder()
                        .id(idx.getAndIncrement())
                        .eventId(event.getId())
                        .titleImage(event.getTitleImage())
                        .name(event.getName())
                        .description(event.getDescription())
                        .build())
                .collect(Collectors.toList());
        return eventDtoList ;

    }


    @Override
    public List<EventListDto> getAllEvent() {
        //List<Event> eventList = iEventRepository.findAll();
        AtomicLong idx = new AtomicLong(1L);
        List<EventListDto> eventListDtoList = iEventRepository.findAllByNow(Boolean.TRUE).stream()
                        .map(element -> EventListDto.builder()
                                .id(idx.getAndIncrement())       //이벤트 index(순서)
                                .eventId(element.getId())
                                .name(element.getName())
                                .description(element.getDescription())
                                .build()).collect(Collectors.toList());

        return eventListDtoList;
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
