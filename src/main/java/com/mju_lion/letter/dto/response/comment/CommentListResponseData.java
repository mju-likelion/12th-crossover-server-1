package com.mju_lion.letter.dto.response.comment;

import lombok.Getter;

import java.util.List;

@Getter
public class CommentListResponseData {
    private final List<CommentResponseData> commentList;
    public CommentListResponseData(List<CommentResponseData> commentList) {
        this.commentList = commentList;
    }
}
