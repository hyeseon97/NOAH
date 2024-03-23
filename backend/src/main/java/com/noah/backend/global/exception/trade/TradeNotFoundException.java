package com.noah.backend.global.exception.trade;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class TradeNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;

    public TradeNotFoundException() {
        this.errorCode = ErrorCode.TRADE_NOT_FOUND;
    }
}
