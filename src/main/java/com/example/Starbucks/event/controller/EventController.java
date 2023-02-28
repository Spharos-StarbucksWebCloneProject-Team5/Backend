package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.service.IEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/event")
@RequiredArgsConstructor
public class EventController {
    private final IEventService iEventService;

    @PostMapping("/add")
    public void addEvent(@RequestBody Event event){iEventService.addEvent(event);}

    @GetMapping("get/{eventId}")
    public Event getEvent(@PathVariable Long eventId){
        return iEventService.getEvent(eventId);
    }

    @GetMapping("get/all")
    public List<Event> getAllEvent(){
        return iEventService.getAllEvent();
    }

    @PostMapping("/update")
    public void updateEvent(@RequestBody Event event){
        iEventService.updateEvent(event);
    }

}
