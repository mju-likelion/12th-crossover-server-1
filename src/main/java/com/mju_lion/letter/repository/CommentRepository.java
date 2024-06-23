package com.mju_lion.letter.repository;

import com.mju_lion.letter.entity.Board;
import com.mju_lion.letter.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
@Repository
public interface CommentRepository extends JpaRepository<Comment, UUID> {
    List<Comment> findAllByBoardIdOrderByCreatedAtAsc(UUID boardId);
    Page<Comment> findByBoard(Board board, Pageable pageable);
}
