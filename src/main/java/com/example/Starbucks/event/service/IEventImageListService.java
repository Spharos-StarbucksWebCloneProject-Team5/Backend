package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventImageList;
import java.util.List;

public interface IEventImageListService {
    void addEventImage(EventImageList eventImageList);


    List<EventImageList> getByEventId(Long eventId);

    List<EventImageList> getAll() ;
    void updateEventImageList(EventImageList eventImageList);
}
