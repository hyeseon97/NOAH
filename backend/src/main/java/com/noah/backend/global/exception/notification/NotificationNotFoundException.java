package com.noah.backend.global.exception.notification;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NotificationNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
    public NotificationNotFoundException(){
        this.errorCode = ErrorCode.NOTIFICATION_NOT_FOUND;
    }
}
