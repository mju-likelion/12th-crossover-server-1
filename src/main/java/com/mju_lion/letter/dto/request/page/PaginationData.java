package com.mju_lion.letter.dto.request.page;

import lombok.Getter;

@Getter
public class PaginationData {
    // 페이지 사이즈 고정
    private static final int FIXED_SIZE = 10;

    private int page;
    private int size = FIXED_SIZE;

    public PaginationData(int page) {
        // page - 1 값이 음수일 시 0으로 설정
        this.page = Math.max(page - 1, 0);
    }
}
