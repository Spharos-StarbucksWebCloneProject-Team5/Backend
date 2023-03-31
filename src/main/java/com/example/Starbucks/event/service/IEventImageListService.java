package com.example.Starbucks.event.service;

import com.example.Starbucks.event.dto.EventImageListDto;
import com.example.Starbucks.event.vo.RequestEventImageList;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEventImageListService {
    void addEventImage(RequestEventImageList requestEventImageList);
    ResponseEntity<?> getByEventId(Long eventId);
    List<EventImageListDto> getAll() ;
    void updateEventImageList(Long id, RequestEventImageList requestEventImageList);
    void deleteEventImageList(Long id);
}
