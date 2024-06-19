package com.mju_lion.letter.repository;

import com.mju_lion.letter.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface BoardRepository extends JpaRepository<Board, UUID> {

}
