package com.noah.backend.global.exception.travelmember;

import com.noah.backend.global.format.response.ErrorCode;

public class MemberTravelNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberTravelNotFound( ) {
        this.errorCode = ErrorCode.MEMBER_NOT_FOUND;
    }
}
