package com.noah.backend.global.exception.notification;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class NotificationSendFailedException extends RuntimeException{
    private final ErrorCode errorCode;
    public NotificationSendFailedException(){
        this.errorCode = ErrorCode.NOTIFICATION_SEND_FAILED;
    }
}
