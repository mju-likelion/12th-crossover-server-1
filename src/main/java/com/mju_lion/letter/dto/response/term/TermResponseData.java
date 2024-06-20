package com.mju_lion.letter.dto.response.term;

import com.mju_lion.letter.entity.Term;
import lombok.Getter;

@Getter
public class TermResponseData {
    private String id;
    private String title;
    private String terms;

    public TermResponseData(Term term) {
        this.id = String.valueOf(term.getId());
        this.title = term.getTitle();
        this.terms = term.getTerms();
    }
}
