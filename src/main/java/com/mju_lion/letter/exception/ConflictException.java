package com.mju_lion.letter.exception;

import com.mju_lion.letter.exception.errorcode.ErrorCode;

public class ConflictException extends CustomException {
    public ConflictException(ErrorCode errorCode){
        super(errorCode);
    }
}