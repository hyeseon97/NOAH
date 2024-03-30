package com.noah.backend.global.exception.membertravel;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class MemberTravelAlreadyExistException extends RuntimeException {
    private final ErrorCode errorCode;

    public MemberTravelAlreadyExistException() {
        this.errorCode = ErrorCode.MEMBERTRAVEL_ALREADY_EXIST;
    }
}
