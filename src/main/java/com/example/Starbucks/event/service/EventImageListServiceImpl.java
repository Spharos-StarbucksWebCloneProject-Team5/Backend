package com.example.Starbucks.event.service;


import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.repository.IEventImageListRepository;
import com.example.Starbucks.event.repository.IEventRepository;
import com.example.Starbucks.event.vo.RequestEvent;
import com.example.Starbucks.event.vo.RequestEventImageList;
import com.example.Starbucks.event.vo.ResponseEvent;
import com.example.Starbucks.event.vo.ResponseEventImageList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<ResponseEventImageList> getByEventId(Long eventId) {
        List<EventImageList> eventImageList = iEventImageListRepository.findAllByEventId(eventId);
        List<ResponseEventImageList> responseEventImageList = new ArrayList<>();
        for(int i=0; i<eventImageList.size(); i++){
            responseEventImageList.add(ResponseEventImageList.builder()
                    .id(eventImageList.get(i).getId())
                    .eventId(eventImageList.get(i).getEvent().getId())
                    .image(eventImageList.get(i).getImage())
                    .build());

        }
        return responseEventImageList;
    }

    @Override
    public List<ResponseEventImageList> getAll() {
        List<EventImageList> eventImageList = iEventImageListRepository.findAll();
        List<ResponseEventImageList> responseEventImageList = new ArrayList<>();
        for(int i=0; i<eventImageList.size(); i++){
            responseEventImageList.add(ResponseEventImageList.builder()
                    .id(eventImageList.get(i).getId())
                    .eventId(eventImageList.get(i).getEvent().getId())
                    .image(eventImageList.get(i).getImage())
                    .build());

        }
        return responseEventImageList;
    }

    @Override
    public void updateEventImageList(Long id, RequestEventImageList requestEventImageList){

        EventImageList eventImageList = iEventImageListRepository.findById(id).get();
        eventImageList.setImage(requestEventImageList.getImage());

        iEventImageListRepository.save(eventImageList);
    }

    @Override
    public void deleteEventImageList(Long id) {
        EventImageList eventImageList = iEventImageListRepository.findById(id).get();
        iEventImageListRepository.delete(eventImageList);
    }
}
