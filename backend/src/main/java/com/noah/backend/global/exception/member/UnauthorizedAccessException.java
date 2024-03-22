package com.noah.backend.global.exception.member;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class UnauthorizedAccessException extends RuntimeException {

    private final ErrorCode errorCode;

    public UnauthorizedAccessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}