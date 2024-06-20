package com.mju_lion.letter.dto.request.comment;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    @NotNull(message = "댓글이 비어있습니다.")
    @Length(min = 1, max = 100, message = "댓글은 최소 1글자 이상, 100글자 이하로 작성해주세요.")
    private String content;
}
