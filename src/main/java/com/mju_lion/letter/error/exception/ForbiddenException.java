package com.mju_lion.letter.error.exception;

import com.mju_lion.letter.error.ErrorCode;

//접근 안됨
public class ForbiddenException extends CustomException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}