package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.dto.EventDto;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.service.IEventService;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventName;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/v1/api/events")
@RequiredArgsConstructor
public class EventController {
    private final IEventService iEventService;
    @PostMapping("")
    public void addEvent(@RequestBody RequestEvent requestEvent) {
        iEventService.addEvent(requestEvent);
    }

    @GetMapping("{id}")//이벤트 가져오기
    public ResponseEvent getEvent(@PathVariable Long id) {
        return iEventService.getEvent(id);
    }

    @ResponseBody
    @GetMapping("category")// 카테고리 목록 띄우기
    public List<ResponseEventName> getEventNames() {
        return iEventService.getEventName();
    }

    @GetMapping("all")//모든 이벤트 가져오기
    public List<Event> getAllEvent() {
        return iEventService.getAllEvent();
    }

    @PutMapping("{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody RequestEvent requestEvent) {
        iEventService.updateEvent(id, requestEvent);
    }

    @DeleteMapping("{id}")
    public void deleteEvent(@PathVariable Long id) {
        iEventService.deleteEvent(id);
    }

}
