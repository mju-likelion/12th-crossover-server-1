package com.mju_lion.letter.controller;

import com.mju_lion.letter.authentication.AuthenticatedUser;
import com.mju_lion.letter.dto.request.board.BoardCreateDto;
import com.mju_lion.letter.dto.request.page.PaginationData;
import com.mju_lion.letter.dto.response.ResponseDto;
import com.mju_lion.letter.dto.response.board.BoardListResponseData;
import com.mju_lion.letter.dto.response.board.BoardResponseData;
import com.mju_lion.letter.entity.User;
import com.mju_lion.letter.service.BoardService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<ResponseDto> createBoard(@AuthenticatedUser User user,
                                                   @RequestBody @Valid BoardCreateDto boardCreateDto) {
        boardService.createBoard(user, boardCreateDto);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.CREATED, "게시물 생성 완료"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<ResponseDto> getAllBoards(@RequestParam(value = "page", defaultValue = "1") int page) {
        PaginationData paginationData = new PaginationData(page);
        BoardListResponseData boardList = boardService.getAllBoards(paginationData);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "전체 게시물 조회 완료", boardList), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto> getBoardById(@PathVariable("id") UUID boardId) {
        BoardResponseData board = boardService.getBoardById(boardId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "단건 게시물 조회 완료", board), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto> deleteBoard(@PathVariable("id") UUID boardId,
                                                   @AuthenticatedUser User user) {
        boardService.deleteBoard(user, boardId);
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "게시물 삭제 완료"), HttpStatus.OK);
    }
}
