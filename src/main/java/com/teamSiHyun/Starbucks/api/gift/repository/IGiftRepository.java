package com.teamSiHyun.Starbucks.api.gift.repository;

import com.teamSiHyun.Starbucks.api.gift.model.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IGiftRepository extends JpaRepository<Gift, Long> {
    List<Gift> findAllBySendId(String sendId);
}
