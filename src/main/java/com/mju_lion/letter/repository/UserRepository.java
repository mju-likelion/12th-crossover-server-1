package com.mju_lion.letter.repository;

import com.mju_lion.letter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUserId(String userId);

    Optional<User> findByName(String name);

    Optional<User> findByEmail(String email);
}