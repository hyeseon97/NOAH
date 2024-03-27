package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class A1001Exception extends RuntimeException{

    private ErrorCode errorCode;

    public A1001Exception(){
        this.errorCode = ErrorCode.BANK_CODE_NOT_AVAILIABLE;
    }
}
