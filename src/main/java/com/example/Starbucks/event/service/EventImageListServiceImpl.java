package com.example.Starbucks.event.service;


import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.model.EventImageList;
import com.example.Starbucks.event.repository.IEventImageListRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class EventImageListServiceImpl implements IEventImageListService {

    private final IEventImageListRepository iEventImageListRepository;
    @Override
    public void addEventImage(EventImageList eventImageList) {
        iEventImageListRepository.save(eventImageList);
    }

    @Override
    public List<EventImageList> getByEventId(Long eventId) {
        return iEventImageListRepository.findAllByEventId(eventId);
    }

    @Override
    public List<EventImageList> getAll() {
        return iEventImageListRepository.findAll();
    }

    @Override
    public void updateEventImageList(EventImageList eventImageList){

        EventImageList eventImageList1 = iEventImageListRepository.findById(eventImageList.getId()).get();
        eventImageList1.setImage(eventImageList.getImage());
        //eventImageList1.set(eventImageList.getUpdateDate());


        iEventImageListRepository.save(eventImageList1);
    }
}
