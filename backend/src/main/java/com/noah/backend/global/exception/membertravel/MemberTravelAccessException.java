package com.noah.backend.global.exception.membertravel;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class MemberTravelAccessException extends RuntimeException {
    private final ErrorCode errorCode;

    public MemberTravelAccessException() {
        this.errorCode = ErrorCode.MEMBERTRAVEL_ACCESS_DENIED;
    }
}
