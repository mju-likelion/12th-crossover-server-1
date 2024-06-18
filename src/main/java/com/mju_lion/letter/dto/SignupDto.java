package com.mju_lion.letter.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupDto {
    //user_id
    @NotBlank(message = "영문과 숫자를 조합하여 5~10글자 미만으로 입력하여 주세요.")
    @Size(min=5,max=100,message = " 5~10글자 미만으로 입력하여 주세요.")
    private String userId;

    //password
    @NotBlank(message = "영문과 숫자,특수기호를 조합하여 4~8글자 미만으로 입력하여 주세요.")
    @Size(min = 5, max = 100,message = " 4~8글자 미만으로 입력하여 주세요.")
    private String password;

    //email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "이메일이 형식에 맞지 않습니다.")
    @NotBlank(message = "사용하실 이메일을 입력해주세요.")
    @Size(min = 1,max = 100,message = "이메일은 최소 한글자 최대 100글자 입니다")
    private String email;

    //name
    @NotBlank(message = "이름이 누락되었습니다.")
    @Size(min = 1,max = 20,message = "이름은 최소 한글자 최대 20글자 입니다")
    private String name;

    //이용약관

}
