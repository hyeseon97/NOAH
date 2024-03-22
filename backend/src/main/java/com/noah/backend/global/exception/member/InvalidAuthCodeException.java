package com.noah.backend.global.exception.member;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class InvalidAuthCodeException extends RuntimeException {

    private final ErrorCode errorCode;

    public InvalidAuthCodeException() {
        this.errorCode = ErrorCode.AUTH_CODE_INVALID;
    }
}
