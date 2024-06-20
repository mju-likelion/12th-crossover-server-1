package com.mju_lion.letter.exception;

import com.mju_lion.letter.exception.errorcode.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}