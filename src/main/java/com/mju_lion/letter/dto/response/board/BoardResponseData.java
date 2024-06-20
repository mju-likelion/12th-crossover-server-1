package com.mju_lion.letter.dto.response.board;

import com.mju_lion.letter.dto.response.comment.CommentResponseData;
import com.mju_lion.letter.entity.Board;
import com.mju_lion.letter.entity.Comment;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Builder
public class BoardResponseData {
    private String id;
    private String name;
    private String title;
    private String content;
    private LocalDateTime createdTime;
    private List<CommentResponseData> commentList; // 댓글 목록 추가

    public static class BoardResponseDataBuilder {
        public BoardResponseDataBuilder board(Board board) {
            this.id = String.valueOf(board.getId());
            this.name = board.getUser().getName();
            this.title = board.getTitle();
            this.content = board.getContent();
            this.createdTime = board.getCreatedAt();
            return this;
        }

        public BoardResponseDataBuilder comments(List<Comment> commentList) {
            this.commentList = commentList.stream()
                    .map(comment -> new CommentResponseData(comment))
                    .collect(Collectors.toList());
            return this;
        }
    }
}
