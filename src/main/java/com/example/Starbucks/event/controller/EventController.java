package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.dto.EventListDto;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.service.IEventService;
import com.example.Starbucks.event.vo.RequestEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/v1/api/events")
@RequiredArgsConstructor
//@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EventController {
    private final IEventService iEventService;

    @Operation(summary = "이벤트 추가", description = "이벤트 추가하기", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Event.class)))
    @PostMapping("")
    public ResponseEntity<Void> addEvent(@RequestBody RequestEvent requestEvent) {
        log.info("controller isNow 값 : "+requestEvent.toString());
        iEventService.addEvent(requestEvent);

        return ResponseEntity.ok().build();
    }

//    @Operation(summary = "  ", description = "이벤트 추가하기", tags = {"Event Controller"})
//    @ApiResponse(responseCode = "200", description = "OK")
//    @GetMapping("{id}")//이벤트 가져오기
//    public ResponseEntity<ResponseEvent> getEvent(@PathVariable Long id) {
//        return ResponseEntity.ok(iEventService.getEvent(id));
//    }

//    @ResponseBody
//    @GetMapping("category")// 카테고리 목록 띄우기
//    public ResponseEntity<List<ResponseEventName>> getEventNames() {
//        return ResponseEntity.ok(iEventService.getEventName());
//    }

    @Operation(summary = "진행중 이벤트 목록", description = "진행중인 이벤트 확인 메인페이지 및 기획전 카테고리", tags = {"Event Controller"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventListDto.class)))
    @GetMapping("all")//모든 이벤트 가져오기
    public ResponseEntity<List<EventListDto>> getAllEvent() {
        return ResponseEntity.ok(iEventService.getAllEvent());
    }

    @Operation(summary = "이벤트 변경", description = "이벤트 추가하기", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Event.class)))
    @PutMapping("{id}")
    public ResponseEntity<Void> updateEvent(@PathVariable Long id, @RequestBody @Valid RequestEvent requestEvent) {
        iEventService.updateEvent(id, requestEvent);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이벤트 삭제", description = "이벤트 삭제", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = Event.class)))
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long id) {
        iEventService.deleteEvent(id);
        return ResponseEntity.ok().build();
    }

}
