package com.example.Starbucks.users.repository;

import com.example.Starbucks.users.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
