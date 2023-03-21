package com.example.Starbucks.event.controller;

import com.example.Starbucks.event.dto.EventImageListDto;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.service.IEventImageListService;
import com.example.Starbucks.event.vo.RequestEventImageList;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

    @Operation(summary = "이벤트 이미지등록", description = "이벤트 이미지 추가하기", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventImageList.class)))
    @PostMapping("")
    public ResponseEntity<Void> addEventImage(@RequestBody @Valid RequestEventImageList requestEventImageList){
        iEventImageListService.addEventImage(requestEventImageList);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이벤트 이미지불러오기", description = "이벤트별 이미지정보 불러오기", tags = {"Event Controller"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventImageListDto.class)))
    @GetMapping("{eventId}")
    public ResponseEntity<List<EventImageListDto>> getEventImage(@PathVariable Long eventId){//eventId에 해당하는 이미지 불러오기
        return ResponseEntity.ok(iEventImageListService.getByEventId(eventId));
    }

    @Operation(summary = "이벤트 이미지수정", description = "이벤트 이미지 수정하기", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventImageListDto.class)))
    @PutMapping("{id}")
    public ResponseEntity<Void> updateEventImageList(@PathVariable Long id, @RequestBody @Valid RequestEventImageList requestEventImageList){
        iEventImageListService.updateEventImageList(id, requestEventImageList);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "이벤트 이미지삭제", description = "이벤트 이미지 삭제하기", tags = {"admin"})
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema(implementation = EventImageList.class)))
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteEventImageList(@PathVariable Long id) {
        iEventImageListService.deleteEventImageList(id);
        return ResponseEntity.ok().build();
    }
}
