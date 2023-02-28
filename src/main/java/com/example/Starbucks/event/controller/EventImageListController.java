package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.service.IEventImageListService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/event-image")
@RequiredArgsConstructor
public class EventImageListController {

    private final IEventImageListService iEventImageListService;

    @PostMapping("/add")
    public void addEventImage(@RequestBody EventImageList eventImageList){
        iEventImageListService.addEventImage(eventImageList);
    }

    @GetMapping("/get/{eventId}")
    public List<EventImageList> getEventImage(@PathVariable Long eventId){
        return iEventImageListService.getByEventId(eventId);
    }
    @PostMapping("/update")
    public void updateEventImageList(@RequestBody EventImageList eventImageList){
        iEventImageListService.updateEventImageList(eventImageList);
    }

    /*@GetMapping("/get/all")
    public List<ProductImageList> getAllCategory(){
        return iProductImageListService.getAll();
    }
*/
}
