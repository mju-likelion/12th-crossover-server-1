package com.mju_lion.letter.controller;

import com.mju_lion.letter.dto.response.ResponseDto;
import com.mju_lion.letter.dto.response.term.TermListResponseData;
import com.mju_lion.letter.dto.response.term.TermResponseData;
import com.mju_lion.letter.service.TermService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/terms")
public class TermController {

    private final TermService termService;

    @GetMapping
    public ResponseEntity<ResponseDto> getAllTerms() {
        TermListResponseData termList = termService.getAllTerms();
        return new ResponseEntity<>(ResponseDto.res(HttpStatus.OK, "약관 전체 조회 완료", termList), HttpStatus.OK);
    }
}
