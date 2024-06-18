package com.mju_lion.letter.error;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //인증
    PASSWORD_NOT_EQUAL("4010", "비밀번호를 잘못 입력하셨습니다. 다시 입력해 주세요."),

    //리소스 중복
    USERID_DUPLICATION("4090", "사용할수 없는 아이디 입니다"),

    //인가
    TOKEN_NOT_FOUND("4031", "토큰이 없습니다"),
    TOKEN_INVALID("4032", "토큰이 유효하지 않습니다"),
    PASSWORD_("4032", "토큰이 유효하지 않습니다"),
    //404
    USER_NOT_FOUND("4041",""),
    USERID_NOT_FOUND("4041","잘못된 아이디를 입력하셨습니다 다시 입력해 주세요 "),

    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 빈 값이거나 공백으로 되어있습니다."),
    REGEX("9003", "형식에 맞지 않습니다."),
    LENGTH("9004", "길이가 유효하지 않습니다.");


    private final String code;
    private final String message;

    //Dto의 어노테이션을 통해 발생한 에러코드를 반환
    public static ErrorCode resolveValidationErrorCode(String code) {
        return switch (code) {
            case "NotNull" -> NOT_NULL;
            case "NotBlank" -> NOT_BLANK;
            case "Pattern" -> REGEX;
            case "Length" -> LENGTH;
            default -> throw new IllegalArgumentException("Unexpected value: " + code);
        };
    }
}
