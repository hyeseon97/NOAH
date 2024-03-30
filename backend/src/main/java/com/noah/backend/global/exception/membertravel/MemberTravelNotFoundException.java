package com.noah.backend.global.exception.membertravel;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class MemberTravelNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public MemberTravelNotFoundException() {
        this.errorCode = ErrorCode.MEMBERTRAVEL_NOT_FOUND;
    }
}
