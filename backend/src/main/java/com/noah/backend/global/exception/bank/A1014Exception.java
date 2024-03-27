package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class A1014Exception extends RuntimeException{

    private ErrorCode errorCode;

    public A1014Exception(){
        this.errorCode = ErrorCode.TRANSACTION_BALANCE_LACK;
    }
}
