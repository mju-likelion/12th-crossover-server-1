package com.mju_lion.letter.error.exception;

import com.mju_lion.letter.error.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode){
        super(errorCode);
    }
}