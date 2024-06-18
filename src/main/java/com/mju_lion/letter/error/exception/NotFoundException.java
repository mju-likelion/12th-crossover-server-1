package com.mju_lion.letter.error.exception;

import com.mju_lion.letter.error.ErrorCode;

public class NotFoundException extends CustomException {
    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}