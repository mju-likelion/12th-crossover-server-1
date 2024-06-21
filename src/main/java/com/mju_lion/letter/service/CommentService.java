package com.mju_lion.letter.service;

import com.mju_lion.letter.dto.request.comment.CommentCreateDto;
import com.mju_lion.letter.dto.response.comment.CommentListResponseData;
import com.mju_lion.letter.dto.response.comment.CommentResponseData;
import com.mju_lion.letter.entity.Board;
import com.mju_lion.letter.entity.Comment;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.exception.errorcode.ErrorCode;
import com.mju_lion.letter.exception.ForbiddenException;
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
        Board board = validateBoard(boardId);

        Comment comment = Comment.builder()
                .user(user)
                .board(board)
                .content(commentCreateDto.getContent())
                .build();

        commentRepository.save(comment);
    }

    private Board validateBoard(UUID boardId) {
        return boardRepository.findById(boardId).orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }

    /**
     * 전체 댓글 조회
     */
    public CommentListResponseData getAllComments(UUID boardId, int page) {
        // 게시물 검증
        Board board = validateBoard(boardId);

        // 한 페이지당 10개씩 노출
        int size = 10;
        // 생성 시간을 기준으로 오름차순 정렬
        Sort sort = Sort.by(Sort.Order.asc("createdAt"));
        // 0-based index 이므로 페이지 번호에서 -1, size, sort 로 페이지 요청 설정
        Pageable pageable = PageRequest.of(page - 1, size, sort);
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
        validateBoard(boardId);

        // 댓글 검증
        Comment comment = validateComment(commentId);

        // 게시물에 속해있는 댓글 검증
        if (!comment.getBoard().getId().equals(boardId)) {
            throw new ForbiddenException(ErrorCode.COMMENT_NOT_BELONG_TO_BOARD);
        }

        // 댓글 작성자 검증
        if (!comment.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException(ErrorCode.COMMENT_NOT_MATCH);
        }

        commentRepository.delete(comment);
    }

    private Comment validateComment(UUID commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> new NotFoundException(ErrorCode.COMMENT_NOT_FOUND));
    }
}
