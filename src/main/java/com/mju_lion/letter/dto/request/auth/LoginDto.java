package com.mju_lion.letter.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class LoginDto {
    //로그인은 userIdd와 password로 한다

    //User_Id
    @NotBlank(message = "영문과 숫자를 조합하여 5~10글자 미만으로 입력하여 주세요.")
    @Size(min=5,max=10,message = "5~10글자 미만으로 입력하여 주세요.")
    private String userId;

    //password
    @NotBlank(message = "영문과 숫자,특수기호를 조합하여 4~8글자 미만으로 입력하여 주세요.")
    @Size(message = "4~8글자 미만으로 입력하여 주세요.", min = 4, max = 8)
    private String password;
}
