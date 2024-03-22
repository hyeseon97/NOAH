package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class BankAccountCreateFailed extends RuntimeException{

    private ErrorCode errorCode;

    public BankAccountCreateFailed(){
        this.errorCode = ErrorCode.BANK_ACCOUNT_CREATE_FAILED;
    }
}
