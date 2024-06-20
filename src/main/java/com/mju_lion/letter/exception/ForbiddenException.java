package com.mju_lion.letter.exception;

import com.mju_lion.letter.exception.errorcode.ErrorCode;

//접근 안됨
public class ForbiddenException extends CustomException {
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }
}