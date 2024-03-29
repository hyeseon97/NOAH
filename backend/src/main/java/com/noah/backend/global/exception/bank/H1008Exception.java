package com.noah.backend.global.exception.bank;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class H1008Exception extends RuntimeException{

    private ErrorCode errorCode;

    public H1008Exception(){
        this.errorCode = ErrorCode.API_KEY_NOT_AVAILIABLE;
    }
}
