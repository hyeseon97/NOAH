package com.noah.backend.global.exception.member;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class FailedMessageTransmissionException extends RuntimeException {

    private final ErrorCode errorCode;

    public FailedMessageTransmissionException() {
        this.errorCode = ErrorCode.EMAIL_SEND_FAILED;
    }
}
