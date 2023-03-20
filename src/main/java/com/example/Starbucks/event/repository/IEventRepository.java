package com.example.Starbucks.event.repository;

import com.example.Starbucks.event.dto.EventDto;
import com.example.Starbucks.event.model.Event;
import com.example.Starbucks.event.vo.ResponseEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByNow(boolean now);
}
