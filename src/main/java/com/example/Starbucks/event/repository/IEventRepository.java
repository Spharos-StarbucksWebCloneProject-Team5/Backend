package com.example.Starbucks.event.repository;

import com.example.Starbucks.event.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEventRepository extends JpaRepository<Event,Long> {

}
