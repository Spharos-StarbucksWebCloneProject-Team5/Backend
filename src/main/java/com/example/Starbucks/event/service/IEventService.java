package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.Event;


import java.util.List;


public interface IEventService {
    void addEvent(Event event);
    Event getEvent(Long eventId);
    List<Event> getAllEvent();

    void updateEvent(Event event);
}
