package com.mju_lion.letter.exception;


import com.mju_lion.letter.exception.errorcode.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {
    private final ErrorCode errorCode;
    private final String detail;

    public CustomException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.detail = null;
    }

    public CustomException(ErrorCode errorCode, String detail) {
        this.errorCode = errorCode;
        this.detail = detail;
    }
}
