package com.mju_lion.letter.entity;

import com.mju_lion.letter.exception.ForbiddenException;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends BaseEntity {
    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    public void validateCommentByUserId(Comment comment, User user) {
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException(ErrorCode.COMMENT_NOT_MATCH);
        }
    }

    public void validateCommentByBoardId(Comment comment, Board board) {
        if (!comment.getBoard().getId().equals(board.getId())) {
            throw new ForbiddenException(ErrorCode.COMMENT_NOT_BELONG_TO_BOARD);
        }
    }
}
