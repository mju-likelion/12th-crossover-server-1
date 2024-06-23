package com.mju_lion.letter.entity;

import com.mju_lion.letter.exception.ForbiddenException;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends BaseEntity{
    @Setter
    @Column(length = 100, nullable = false)
    private String title;

    @Setter
    @Column(length = 1000, nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "board")
    private List<Comment> comments;

    public void validateBoardByUserId(Board board, User user) {
        if (!board.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException(ErrorCode.BOARD_NOT_MATCH);
        }
    }
}
