package com.noah.backend.global.exception.travelmember;

import com.noah.backend.global.format.response.ErrorCode;

public class MemberTravelUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberTravelUpdateFailed( ) {
        this.errorCode = ErrorCode.MEMBERTRAVEL_UPDATE_FAILED;
    }
}
