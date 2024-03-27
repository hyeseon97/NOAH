package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class A1018Exception extends RuntimeException{

    private ErrorCode errorCode;

    public A1018Exception(){
        this.errorCode = ErrorCode.TRANSACTION_SUMMARY_NOT_AVAILIABLE;
    }
}
