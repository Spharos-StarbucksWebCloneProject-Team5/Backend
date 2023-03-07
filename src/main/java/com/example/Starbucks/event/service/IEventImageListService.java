package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.vo.RequestEventImageList;

import java.util.List;

public interface IEventImageListService {
    void addEventImage(RequestEventImageList requestEventImageList);
    List<EventImageList> getByEventId(Long eventId);
    List<EventImageList> getAll() ;
    void updateEventImageList(Long id, RequestEventImageList requestEventImageList);
    void deleteEventImageList(Long id);
}
