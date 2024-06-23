package com.mju_lion.letter.service;

import com.mju_lion.letter.dto.request.comment.CommentCreateDto;
import com.mju_lion.letter.dto.request.page.PaginationData;
import com.mju_lion.letter.dto.response.comment.CommentListResponseData;
import com.mju_lion.letter.dto.response.comment.CommentResponseData;
import com.mju_lion.letter.entity.Board;
import com.mju_lion.letter.entity.Comment;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import com.mju_lion.letter.exception.NotFoundException;
import com.mju_lion.letter.repository.BoardRepository;
import com.mju_lion.letter.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentService {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    /**
     * 댓글 작성
     */
    public void createComment(User user, UUID boardId, CommentCreateDto commentCreateDto) {
        // 게시물 검증
        Board board = findExistingBoard(boardId);

        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .content(commentCreateDto.getContent())
                .build();

        commentRepository.save(comment);
    }

    private Board findExistingBoard(UUID boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }

    /**
     * 전체 댓글 조회
     */
    public CommentListResponseData getAllComments(UUID boardId, PaginationData paginationData) {
        // 게시물 검증
        Board board = findExistingBoard(boardId);

        // page, size, sort 로 페이지 요청 설정
        Pageable pageable = PageRequest.of(paginationData.getPage(), paginationData.getSize(), Sort.by(Sort.Order.asc("createdAt")));
        Page<Comment> commentPage = commentRepository.findByBoard(board, pageable);

        // 댓글 목록 가져온 후 매핑
        List<CommentResponseData> commentResponseDataList = commentPage.getContent().stream()
                .map(comment -> new CommentResponseData(comment))
                .collect(Collectors.toList());

        return new CommentListResponseData(commentResponseDataList);
    }

    /**
     * 댓글 삭제
     */
    public void deleteComment(User user, UUID boardId, UUID commentId) {

        // 게시물 검증
        Board board = findExistingBoard(boardId);

        // 댓글 검증
        Comment comment = findExistingComment(commentId);

        // 게시물에 속해있는 댓글 검증
        comment.validateCommentByBoardId(comment, board);

        // 댓글 작성자 검증
        comment.validateCommentByUserId(comment, user);

        commentRepository.delete(comment);
    }

    private Comment findExistingComment(UUID commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
