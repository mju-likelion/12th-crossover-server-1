package com.mju_lion.letter.service;

import com.mju_lion.letter.dto.request.board.BoardCreateDto;
import com.mju_lion.letter.dto.request.page.PaginationData;
import com.mju_lion.letter.dto.response.board.BoardListResponseData;
import com.mju_lion.letter.dto.response.board.BoardResponseData;
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
public class BoardService {

    public final BoardRepository boardRepository;
    public final CommentRepository commentRepository;

    /**
     * 게시물 작성
     */
    public void createBoard(User user, BoardCreateDto boardCreateDto) {

        Board board = Board.builder()
                .title(boardCreateDto.getTitle())
                .content(boardCreateDto.getContent())
                .user(user)
                .build();

        boardRepository.save(board);
    }

    /**
     * 게시물 전체 조회
     */
    public BoardListResponseData getAllBoards(PaginationData paginationData) {
        // page, size, sort 로 페이지 요청 설정
        Pageable pageable = PageRequest.of(paginationData.getPage(), paginationData.getSize(), Sort.by(Sort.Order.desc("createdAt")));
        Page<Board> boardPage = boardRepository.findAll(pageable);

        // 게시물 목록 가져온 후 매핑
        List<BoardResponseData> boardResponses = boardPage.getContent().stream()
                .map(board -> BoardResponseData.builder()
                        .board(board)
                        .build())
                .collect(Collectors.toList());

        return new BoardListResponseData(boardResponses);
    }

    /**
     * 게시물 단건 조회
     */
    public BoardResponseData getBoardById(UUID boardId) {
        // 게시물 검증
        Board board = findExistingBoard(boardId);

        // 게시물에 달린 댓글리스트
        List<Comment> commentList = commentRepository.findAllByBoardIdOrderByCreatedAtAsc(boardId);

        return BoardResponseData.builder()
                .board(board)
                .comments(commentList)
                .build();
    }

    private Board findExistingBoard(UUID boardId) {
        return boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
    }

    /**
     * 게시물 삭제
     */
    public void deleteBoard(User user, UUID boardId) {
        // 게시물 검증
        Board board = findExistingBoard(boardId);

        // 게시물 작성자 검증
        board.validateBoardByUserId(board, user);

        boardRepository.delete(board);
    }
}
