package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventName;


import java.util.List;
import java.util.Optional;


public interface IEventService {
    void addEvent(RequestEvent requestEvent);
    ResponseEvent getEvent(Long eventId);
    List<Event> getAllEvent();
    List<ResponseEventName> getEventName();
    void updateEvent(Long id, RequestEvent requestEvent);
    void deleteEvent(Long id);
}
