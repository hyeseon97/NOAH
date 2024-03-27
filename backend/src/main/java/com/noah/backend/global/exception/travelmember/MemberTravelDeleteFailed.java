package com.noah.backend.global.exception.travelmember;

import com.noah.backend.global.format.response.ErrorCode;

public class MemberTravelDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberTravelDeleteFailed( ) {
        this.errorCode = ErrorCode.MEMBERTRAVEL_DELETE_FAILED;
    }
}
