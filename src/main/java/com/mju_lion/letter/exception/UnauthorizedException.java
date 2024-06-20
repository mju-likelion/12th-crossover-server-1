package com.mju_lion.letter.exception;

import com.mju_lion.letter.exception.errorcode.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}