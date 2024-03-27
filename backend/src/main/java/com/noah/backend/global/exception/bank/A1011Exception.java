package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class A1011Exception extends RuntimeException{

    private ErrorCode errorCode;

    public A1011Exception(){
        this.errorCode = ErrorCode.TRANSACTION_BALANCE_NOT_AVAILIABLE;
    }
}
