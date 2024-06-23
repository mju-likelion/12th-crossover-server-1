package com.mju_lion.letter.controller;

import com.mju_lion.letter.authentication.AuthenticatedUser;
import com.mju_lion.letter.dto.request.comment.CommentCreateDto;
import com.mju_lion.letter.dto.request.page.PaginationData;
import com.mju_lion.letter.dto.response.ResponseDto;
import com.mju_lion.letter.dto.response.comment.CommentListResponseData;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/boards/{boardId}/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<ResponseDto> createComment(@AuthenticatedUser User user,
                                                     @PathVariable("boardId") UUID boardId,
                                                     @Valid @RequestBody CommentCreateDto commentCreateDto) {
        commentService.createComment(user, boardId, commentCreateDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "댓글 생성 완료"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllComments(@PathVariable("boardId") UUID boardId,
                                                      @RequestParam(value = "page", defaultValue = "1") int page) {
        PaginationData paginationData = new PaginationData(page);
        CommentListResponseData commentList = commentService.getAllComments(boardId, paginationData);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "전체 댓글 조회 완료", commentList), HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<ResponseDto> deleteComment(@AuthenticatedUser User user,
                                                     @PathVariable("boardId") UUID boardId,
                                                     @PathVariable("commentId") UUID commentId) {
        commentService.deleteComment(user, boardId, commentId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "댓글 삭제 완료"), HttpStatus.OK);
    }
}
