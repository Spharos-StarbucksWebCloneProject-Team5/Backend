package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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
        List<Event> events = iEventRepository.findAll();
        List<ResponseEventName> responseEventName = new ArrayList<>();


        for(int i=0; i< events.size();i++){
            if(events.get(i).isNow()){
                responseEventName.add(ResponseEventName.builder()
                        .id(events.get(i).getId())
                        .name(events.get(i).getName())
                        .build());
            }
        }
        return responseEventName;
    }

    @Override
    public List<Event> getAllEvent() {
        return iEventRepository.findAll();
    }

    @Override
    public void updateEvent(Long id, RequestEvent requestEvent) {

        Event event = iEventRepository.findById(id).get();
        event.setName(requestEvent.getName());
        event.setTitle_image(requestEvent.getTitle_image());
        event.setInfo_image(requestEvent.getInfo_image());
        event.setDescription(requestEvent.getDescription());
        event.setStart_date(requestEvent.getStart_date());
        event.setEnd_date(requestEvent.getEnd_date());
        event.setNow(requestEvent.isNow());

        iEventRepository.save(event);
    }

    @Override
    public void deleteEvent(Long id) {
        Event event = iEventRepository.findById(id).get();
        iEventRepository.delete(event);
    }
}
