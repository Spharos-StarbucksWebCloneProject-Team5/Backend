package com.example.Starbucks.api.event.repository;

import com.example.Starbucks.api.event.model.EventImageList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IEventImageListRepository extends JpaRepository<EventImageList, Long> {
    Optional<EventImageList> findByEventId(Long eventId);
}
