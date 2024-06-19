package com.mju_lion.letter.dto.response.board;

import com.mju_lion.letter.entity.Board;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BoardResponseData {
    private String id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdTime;

    public BoardResponseData(Board board) {
        this.id = board.getId().toString();
        this.name = board.getUser().getName();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.createdTime = board.getCreatedAt();
    }
}
