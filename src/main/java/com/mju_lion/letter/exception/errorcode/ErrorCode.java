package com.mju_lion.letter.exception.errorcode;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    //UnauthorizedException
    INVALID_PASSWORD("4010", "유효하지 않은 비밀번호입니다."),
    COOKIE_NOT_FOUND("4011", "쿠키를 찾을 수 없습니다."),
    INVALID_TOKEN("4012", "유효하지 않은 토큰입니다."),

    //ForbiddenException
    BOARD_NOT_MATCH("4030","해당 게시물에 대한 접근 권한이 없습니다."),
    COMMENT_NOT_MATCH("4031","해당 댓글에 대한 접근 권한이 없습니다."),
    COMMENT_NOT_BELONG_TO_BOARD("4032", "게시물에 속하지 않는 댓글입니다."),

    //NotFoundException
    USER_NOT_FOUND("4040","존재하지 않는 사용자입니다."),
    USERID_NOT_FOUND("4041","존재하지 않는 아이디입니다."),
    BOARD_NOT_FOUND("4042","존재하지 않는 게시물입니다."),
    COMMENT_NOT_FOUND("4043","존재하지 않는 댓글입니다."),
    TERM_NOT_FOUND("4044","존재하지 않는 약관입니다."),

    //ConflictException
    DUPLICATED_USERID("4090", "이미 사용 중인 아이디입니다."),
    DUPLICATED_NAME("4091","이미 사용 중인 이름입니다."),
    DUPLICATED_EMAIL("4092", "이미 사용 중인 이메일입니다."),

    //ValidationException
    NOT_NULL("9001", "필수값이 누락되었습니다."),
    NOT_BLANK("9002", "필수값이 빈 값이거나 공백으로 되어있습니다."),
    REGEX("9003", "이메일 형식에 맞지 않습니다."),
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
