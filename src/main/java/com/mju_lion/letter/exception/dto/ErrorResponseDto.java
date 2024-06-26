package com.mju_lion.letter.exception.dto;


import com.mju_lion.letter.exception.CustomException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public class ErrorResponseDto {
    private final String errorCode;
    private final String message;
    private final String detail;

    public static ErrorResponseDto res(final CustomException customException) {
        String errorCode = customException.getErrorCode().getCode();
        String message = customException.getErrorCode().getMessage();
        String detail = customException.getDetail();
        return new ErrorResponseDto(errorCode, message, detail);
    }


    //예상치못한 예외 발생
    public static ErrorResponseDto res(final String errorCode, final Exception exception) {
        return new ErrorResponseDto(errorCode, exception.getMessage(), Arrays.toString(exception.getStackTrace()));
    }
}
