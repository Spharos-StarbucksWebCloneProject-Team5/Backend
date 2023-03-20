package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.dto.EventDto;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.service.IEventService;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventName;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@Slf4j
@RequestMapping("/v1/api/events")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
    private final IEventService iEventService;
    @PostMapping("")
    public ResponseEntity<?> addEvent(@RequestBody RequestEvent requestEvent) {
        log.info("controller isNow 값 : "+requestEvent.toString());
        iEventService.addEvent(requestEvent);
        ResponseEntity.ok();

        return null;
    }

    @GetMapping("{id}")//이벤트 가져오기
    public ResponseEntity<ResponseEvent> getEvent(@PathVariable Long id) {
        return ResponseEntity.ok(iEventService.getEvent(id));
    }

    @ResponseBody
    @GetMapping("category")// 카테고리 목록 띄우기
    public ResponseEntity<List<ResponseEventName>> getEventNames() {
        return ResponseEntity.ok(iEventService.getEventName());
    }

    @GetMapping("all")//모든 이벤트 가져오기
    public ResponseEntity<List<ResponseEvent>> getAllEvent() {
        return ResponseEntity.ok(iEventService.getAllEvent());
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateEvent(@PathVariable Long id, @RequestBody @Valid RequestEvent requestEvent) {
        iEventService.updateEvent(id, requestEvent);
       ResponseEntity.ok();
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEvent(@PathVariable Long id) {
        iEventService.deleteEvent(id);
        ResponseEntity.ok();
        return null;
    }

}
