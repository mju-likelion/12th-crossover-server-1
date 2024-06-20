package com.mju_lion.letter.repository;

import com.mju_lion.letter.entity.UserTerm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserTermRepository extends JpaRepository<UserTerm, UUID> {

}
