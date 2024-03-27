package com.noah.backend.global.exception.travelmember;

import com.noah.backend.global.format.response.ErrorCode;

public class MemberTravelPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public MemberTravelPermissionDenied( ) {
        this.errorCode = ErrorCode.MEMBERTRAVEL_PERMISSION_DENIED;
    }
}
