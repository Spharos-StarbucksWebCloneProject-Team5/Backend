package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.service.IEventImageListService;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.RequestEventImageList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/event-images")
@RequiredArgsConstructor
public class EventImageListController {

    private final IEventImageListService iEventImageListService;

    @PostMapping("")
    public void addEventImage(@RequestBody RequestEventImageList requestEventImageList){
        iEventImageListService.addEventImage(requestEventImageList);
    }

    @GetMapping("{eventId}")
    public List<EventImageList> getEventImage(@PathVariable Long eventId){
        return iEventImageListService.getByEventId(eventId);
    }
    @PutMapping("{id}")
    public void updateEventImageList(@PathVariable Long id, @RequestBody RequestEventImageList requestEventImageList){
        iEventImageListService.updateEventImageList(id, requestEventImageList);
    }

    @DeleteMapping("{id}")
    public void deleteEventImageList(@PathVariable Long id) {
        iEventImageListService.deleteEventImageList(id);
    }
}
