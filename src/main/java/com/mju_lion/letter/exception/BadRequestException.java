package com.mju_lion.letter.exception;

import com.mju_lion.letter.exception.errorcode.ErrorCode;

public class BadRequestException extends CustomException{
    public BadRequestException(ErrorCode errorCode, String detail) {
        super(errorCode, detail);
    }
}
