package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.users.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
@RequiredArgsConstructor
public class EventServiceImpl implements IEventService{
    private final IEventRepository iEventRepository;

    @Override
    public void addEvent(Event event) {iEventRepository.save(event);}

    @Override
    public Event getEvent(Long eventId) {
        return iEventRepository.findById(eventId).get();
    }

    @Override
    public List<Event> getAllEvent() {
        return iEventRepository.findAll();
    }

    @Override
    public void updateEvent(Event event){

        Event event1 = iEventRepository.findById(event.getId()).get();
        event1.setName(event.getName());
        event1.setTitle_image(event.getTitle_image());
        event1.setInfo_image(event.getInfo_image());
        event1.setDescription(event.getDescription());
        event1.setStart_date(event.getStart_date());
        event1.setEnd_date(event.getEnd_date());
        event1.set_now(event.is_now());

        iEventRepository.save(event1);
    }
}
