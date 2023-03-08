package com.example.Starbucks.event.repository;


import com.example.Starbucks.event.model.EventProduct;
import com.example.Starbucks.event.vo.ResponseEventProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventProductRepository extends JpaRepository<EventProduct,Long> {
    List<EventProduct> findByProductId(Long productId);
    List<EventProduct> findByEventId(Long eventId);
    List<EventProduct> findAllByEventId(Long eventId);
}
