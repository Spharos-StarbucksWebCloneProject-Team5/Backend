package com.example.Starbucks.event.service;

import com.example.Starbucks.event.dto.EventDto;
import com.example.Starbucks.event.dto.EventListDto;
import com.example.Starbucks.event.vo.RequestEvent;


import java.util.List;


public interface IEventService {
    void addEvent(RequestEvent requestEvent);
    List<EventDto> getEventImage();
    List<EventListDto> getAllEvent();
    void updateEvent(Long id, RequestEvent requestEvent);
    void deleteEvent(Long id);
}
