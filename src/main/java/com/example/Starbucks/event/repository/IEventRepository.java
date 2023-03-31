package com.example.Starbucks.event.repository;

import com.example.Starbucks.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByNow(boolean now);
}
