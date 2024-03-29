package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class H1009Exception extends RuntimeException{

    private ErrorCode errorCode;

    public H1009Exception(){
        this.errorCode = ErrorCode.USER_KEY_NOT_AVAILIABLE;
    }
}
