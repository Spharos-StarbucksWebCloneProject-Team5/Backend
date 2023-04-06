package com.teamSiHyun.Starbucks.api.event.service;

import com.teamSiHyun.Starbucks.api.event.dto.EventDto;
import com.teamSiHyun.Starbucks.api.event.dto.EventListDto;
import com.teamSiHyun.Starbucks.api.event.vo.RequestEvent;


import java.util.List;


public interface IEventService {
    void addEvent(RequestEvent requestEvent);
    List<EventDto> getEventImage();
    List<EventListDto> getAllEvent();
    void updateEvent(Long id, RequestEvent requestEvent);
    void deleteEvent(Long id);
}
