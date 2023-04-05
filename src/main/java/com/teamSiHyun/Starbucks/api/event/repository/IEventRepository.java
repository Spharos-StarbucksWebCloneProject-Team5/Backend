package com.teamSiHyun.Starbucks.api.event.repository;

import com.teamSiHyun.Starbucks.api.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventRepository extends JpaRepository<Event,Long> {
    List<Event> findAllByNow(boolean now);
}
