package com.noah.backend.global.exception.exchange;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ExchangeFailedException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExchangeFailedException() {
        this.errorCode = ErrorCode.EXCHANGE_FAILED;
    }
}
