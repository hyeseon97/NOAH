package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class A1003Exception extends RuntimeException{

    private ErrorCode errorCode;

    public A1003Exception(){
        this.errorCode = ErrorCode.BANK_ACCOUNT_NUMBER_NOT_AVAILIABLE;
    }
}
