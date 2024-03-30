package com.noah.backend.global.exception.notification;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NotificationAccessException extends RuntimeException{
    private final ErrorCode errorCode;
    public NotificationAccessException(){
        this.errorCode = ErrorCode.NOTIFICATION_ACCESS_DENIED;
    }
}
