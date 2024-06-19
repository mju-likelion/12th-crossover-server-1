package com.mju_lion.letter.dto.response.comment;

import com.mju_lion.letter.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseData {
    private String id;
    private String content;
    private String name;
    private LocalDateTime createdTime;

    public CommentResponseData(Comment comment) {
        this.id = String.valueOf(comment.getId());
        this.content = comment.getContent();
        this.name = comment.getUser().getName();
        this.createdTime = comment.getCreatedAt();
    }
}
