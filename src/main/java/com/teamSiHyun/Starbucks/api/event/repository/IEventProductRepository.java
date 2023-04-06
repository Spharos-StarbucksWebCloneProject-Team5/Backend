package com.teamSiHyun.Starbucks.api.event.repository;


import com.teamSiHyun.Starbucks.api.event.model.EventProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IEventProductRepository extends JpaRepository<EventProduct,Long> {
    List<EventProduct> findAllByProductId(Long productId);
    List<EventProduct> findAllByEventId(Long eventId);

}
