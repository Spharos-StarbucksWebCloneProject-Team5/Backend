package com.example.Starbucks.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisService {

    private final int LIMIT_TIME = 3 * 60;
    @Autowired // final 대신 autowired
    private RedisTemplate<String, String> redisTemplate;

    // create
    public void createEmailCertification(String email, String certificationNumber) {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(email, certificationNumber, Duration.ofSeconds(LIMIT_TIME)); // key, value, 데이터 유지 시간
    }

    // read
    public String getEmailCertification(String email) {
        return redisTemplate.opsForValue().get(email);
    }

    // delete
    public void removeEmailCertification(String email) {
        redisTemplate.delete(email);
    }

    public boolean hasKey(String email) {
        return redisTemplate.hasKey(email);
    }
}