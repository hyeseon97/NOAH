package com.noah.backend.global.exception.member;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class AccessTokenNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public AccessTokenNotFoundException() {
        this.errorCode = ErrorCode.EXPIRED_TOKEN;
    }
}
