package com.mju_lion.letter.service;

import com.mju_lion.letter.dto.request.board.BoardCreateDto;
import com.mju_lion.letter.dto.response.board.BoardListResponseData;
import com.mju_lion.letter.dto.response.board.BoardResponseData;
import com.mju_lion.letter.entity.Board;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.error.ErrorCode;
import com.mju_lion.letter.error.exception.ForbiddenException;
import com.mju_lion.letter.error.exception.NotFoundException;
import com.mju_lion.letter.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardService {

    public final BoardRepository boardRepository;

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
    public BoardListResponseData getAllBoards(int page) {

        int size = 10;
        Page<Board> boards = boardRepository.findAll(PageRequest.of(page, size));
        return new BoardListResponseData((List<Board>) boards);
    }

    /**
     * 게시물 단건 조회
     */
    public BoardResponseData getBoardById(UUID boardId) {
        // 게시물 검증
        Board board = validateBoard(boardId);

        return new BoardResponseData(board);
    }

    private Board validateBoard(UUID boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundException(ErrorCode.BOARD_NOT_FOUND));
        return board;
    }

    /**
     * 게시물 삭제
     * @param user
     * @param boardId
     */
    public void deleteBoard(User user, UUID boardId) {
        // 게시물 검증
        Board board = validateBoard(boardId);

        if (!board.getUser().getId().equals(user.getId())) {
            throw new ForbiddenException(ErrorCode.BOARD_NOT_MATCH);
        }

        boardRepository.delete(board);
    }
}
