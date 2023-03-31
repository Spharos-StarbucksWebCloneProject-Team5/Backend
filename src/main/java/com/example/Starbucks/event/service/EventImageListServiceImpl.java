package com.example.Starbucks.event.service;


import com.example.Starbucks.event.dto.EventImageListDto;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.repository.IEventImageListRepository;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEventImageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventImageListServiceImpl implements IEventImageListService {

    private final IEventImageListRepository iEventImageListRepository;
    private final IEventRepository iEventRepository;

    @Override
    public void addEventImage(RequestEventImageList requestEventImageList) {
        EventImageList eventImageList = EventImageList.builder()
                .event(iEventRepository.findById(requestEventImageList.getEventId()).get())
                .image(requestEventImageList.getImage())
                .build();

        iEventImageListRepository.save(eventImageList);
    }
    @Override
    public ResponseEntity<?> getByEventId(Long eventId) {
        Optional<EventImageList> eventImageList = iEventImageListRepository.findByEventId(eventId);
        if(eventImageList.isPresent()){
            EventImageListDto eventImageListDto = EventImageListDto.builder()
                    .id(eventImageList.get().getId())
                    .eventId(eventImageList.get().getEvent().getId())
                    .description(eventImageList.get().getEvent().getDescription())
                    .image(eventImageList.get().getImage())
                    .build();
            return ResponseEntity.ok(eventImageListDto);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
    }

    @Override
    public List<EventImageListDto> getAll() {
        List<EventImageListDto> eventImageListDto = iEventImageListRepository.findAll().stream()
                .map(element -> EventImageListDto.builder()
                        .id(element.getId())
                        .eventId(element.getEvent().getId())
                        .image(element.getImage())
                        .build()).collect(Collectors.toList());

        return eventImageListDto;
    }

    @Override
    public void updateEventImageList(Long id, RequestEventImageList requestEventImageList){

        EventImageList eventImageList = EventImageList.builder()
                .id(id)
                .image(requestEventImageList.getImage())
                .build();

        iEventImageListRepository.save(eventImageList);
    }

    @Override
    public void deleteEventImageList(Long id) {
        EventImageList eventImageList = iEventImageListRepository.findById(id).get();
        iEventImageListRepository.delete(eventImageList);
    }
}
