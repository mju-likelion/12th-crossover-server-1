package com.mju_lion.letter.service;

import com.mju_lion.letter.dto.response.term.TermListResponseData;
import com.mju_lion.letter.dto.response.term.TermResponseData;
import com.mju_lion.letter.entity.Term;
import com.mju_lion.letter.repository.TermRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TermService {

    private final TermRepository termRepository;

    /**
     * 약관 전체 조회
     */
    public TermListResponseData getAllTerms() {
        // 약관 목록 가져온 후 매핑
        List<TermResponseData> termResponseDataList = termRepository.findAll().stream()
                .map(term -> new TermResponseData(term))
                .collect(Collectors.toList());

        return new TermListResponseData(termResponseDataList);
    }
}
