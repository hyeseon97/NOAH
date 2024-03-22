package com.noah.backend.global.exception.member;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class DuplicateEmailException extends RuntimeException {

    private final ErrorCode errorCode;

    public DuplicateEmailException() {
        this.errorCode = ErrorCode.EMAIL_DUPLICATED;
    }
}
