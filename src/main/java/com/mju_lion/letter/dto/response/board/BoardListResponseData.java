package com.mju_lion.letter.dto.response.board;

import com.mju_lion.letter.entity.Board;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardListResponseData {

    private final List<BoardResponseData> boardList;

    public BoardListResponseData(List<BoardResponseData> boardList) {
        this.boardList = boardList;
    }
}
