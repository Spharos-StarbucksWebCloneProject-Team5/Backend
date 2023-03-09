package com.example.Starbucks.event.service;

import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.vo.RequestEventImageList;
import com.example.Starbucks.event.vo.ResponseEventImageList;

import java.util.List;

public interface IEventImageListService {
    void addEventImage(RequestEventImageList requestEventImageList);
    List<ResponseEventImageList> getByEventId(Long eventId);
    List<ResponseEventImageList> getAll() ;
    void updateEventImageList(Long id, RequestEventImageList requestEventImageList);
    void deleteEventImageList(Long id);
}
