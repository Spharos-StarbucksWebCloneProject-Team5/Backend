package com.example.Starbucks.event.repository;

import com.example.Starbucks.event.model.EventImageList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEventImageListRepository extends JpaRepository<EventImageList, Long> {
    Optional<EventImageList> findByEventId(Long eventId);
}
