package com.mju_lion.letter.dto.response.board;

import com.mju_lion.letter.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardListResponseData {

    private final List<BoardResponseData> boardList;

    public BoardListResponseData(List<Board> boardList) {
        this.boardList = boardList.stream()
                .map(board -> new BoardResponseData(board))
                .collect(Collectors.toList());
    }
}
