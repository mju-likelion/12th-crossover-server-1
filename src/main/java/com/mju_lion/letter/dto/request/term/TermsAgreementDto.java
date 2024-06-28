package com.mju_lion.letter.dto.request.term;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.UUID;

@Getter
@AllArgsConstructor
public class TermsAgreementDto {
    private String termId;
    private Boolean agreed;
}
