package com.mju_lion.letter.dto.response.term;

import lombok.Getter;

import java.util.List;

@Getter
public class TermListResponseData {
    private List<TermResponseData> terms;

    public TermListResponseData(List<TermResponseData> termResponseDataList) {
        this.terms = termResponseDataList;
    }
}
