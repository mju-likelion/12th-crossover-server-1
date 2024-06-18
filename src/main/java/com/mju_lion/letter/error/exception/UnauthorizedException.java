package com.mju_lion.letter.error.exception;

import com.mju_lion.letter.error.ErrorCode;

public class UnauthorizedException extends CustomException {
    public UnauthorizedException(ErrorCode errorCode) {
        super(errorCode);
    }
}