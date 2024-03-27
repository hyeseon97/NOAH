package com.noah.backend.global.exception.exchange;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ExchangeCurrencyNotAcceptableException extends RuntimeException {
    private final ErrorCode errorCode;

    public ExchangeCurrencyNotAcceptableException() {
        this.errorCode = ErrorCode.EXCHANGE_CURRENCY_NOT_ACCEPTABLE;
    }
}
