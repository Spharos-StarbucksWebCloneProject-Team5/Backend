package com.example.Starbucks.event.repository;

import com.example.Starbucks.event.model.EventImageList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventImageListRepository extends JpaRepository<EventImageList, Long> {
    List<EventImageList>findByEventId(Long eventId);
}
