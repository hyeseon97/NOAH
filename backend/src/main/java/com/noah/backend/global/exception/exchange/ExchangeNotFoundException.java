package com.noah.backend.global.exception.exchange;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ExchangeNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExchangeNotFoundException() {
        this.errorCode = ErrorCode.EXCHANGE_NOT_FOUND;
    }
}
