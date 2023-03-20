package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.service.IEventImageListService;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.RequestEventImageList;
import com.example.Starbucks.event.vo.ResponseEventImageList;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/event-images")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventImageListController {

    private final IEventImageListService iEventImageListService;

    @PostMapping("")
    public ResponseEntity<?> addEventImage(@RequestBody @Valid RequestEventImageList requestEventImageList){
        ResponseEntity.ok();
        iEventImageListService.addEventImage(requestEventImageList);
        return null;
    }

    @GetMapping("{eventId}")
    public ResponseEntity<List<ResponseEventImageList>> getEventImage(@PathVariable Long eventId){//eventId에 해당하는 이미지 불러오기
        return ResponseEntity.ok(iEventImageListService.getByEventId(eventId));
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateEventImageList(@PathVariable Long id, @RequestBody @Valid RequestEventImageList requestEventImageList){
        iEventImageListService.updateEventImageList(id, requestEventImageList);
        ResponseEntity.ok();
        return null;
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteEventImageList(@PathVariable Long id) {
        iEventImageListService.deleteEventImageList(id);
        ResponseEntity.ok();
        return null;
    }
}
