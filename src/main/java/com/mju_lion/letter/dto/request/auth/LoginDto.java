package com.mju_lion.letter.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginDto {
    //로그인은 userIdd와 password로 한다

    //user_id
    @NotBlank(message = "영문과 숫자를 조합하여 5~10글자 미만으로 입력하여 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{5,10}$", message = "영문과 숫자를 조합하여 5~10글자 미만으로 입력하여 주세요.")
    @Size(min = 5, max = 10, message = " 5~10글자 미만으로 입력하여 주세요.")
    private String userId;

    //password
    @NotBlank(message = "영문과 숫자,특수기호를 조합하여 8~14글자 미만으로 입력하여 주세요.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[!@#$%^&*()])[A-Za-z\\d!@#$%^&*()]{8,14}$", message = "영문과 숫자,특수기호를 조합하여 8~14글자 미만으로 입력하여 주세요.")
    @Size(min = 8, max = 14, message = " 8~14글자 미만으로 입력하여 주세요.")
    private String password;
}
