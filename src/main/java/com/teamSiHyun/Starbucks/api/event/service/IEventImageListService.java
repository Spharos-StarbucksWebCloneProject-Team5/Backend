package com.teamSiHyun.Starbucks.api.event.service;

import com.teamSiHyun.Starbucks.api.event.vo.RequestEventImageList;
import com.teamSiHyun.Starbucks.api.event.dto.EventImageListDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IEventImageListService {
    void addEventImage(RequestEventImageList requestEventImageList);
    ResponseEntity<?> getByEventId(Long eventId);
    List<EventImageListDto> getAll() ;
    void updateEventImageList(Long id, RequestEventImageList requestEventImageList);
    void deleteEventImageList(Long id);
}
