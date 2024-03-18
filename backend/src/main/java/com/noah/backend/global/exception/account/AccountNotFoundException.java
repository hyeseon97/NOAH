package com.noah.backend.global.exception.account;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class AccountNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public AccountNotFoundException() {
        
        this.errorCode = ErrorCode.ACCOUNT_NOT_FOUND;
    }
}
