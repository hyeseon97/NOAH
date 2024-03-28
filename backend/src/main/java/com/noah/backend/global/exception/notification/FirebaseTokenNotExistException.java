package com.noah.backend.global.exception.notification;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FirebaseTokenNotExistException extends RuntimeException{
    private final ErrorCode errorCode;
    public FirebaseTokenNotExistException(){
        this.errorCode = ErrorCode.FIREBASE_TOKEN_NOT_EXIST;
    }
}
