package com.mju_lion.letter.dto.request.board;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
public class BoardCreateDto {
    @NotNull(message = "레터 제목이 비어있습니다.")
    @Length(min = 1, max = 20, message = "레터 제목은 최소 1글자 이상, 20글자 이하로 작성해주세요.")
    private String title;
    @NotNull(message = "내용이 비어있습니다.")
    @Length(min = 1, max = 140, message = "레터 내용은 최소 1글자 이상, 140글자 이하로 작성해주세요.")
    private String content;
}
