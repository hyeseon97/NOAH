package com.noah.backend.global.exception.review;

import com.noah.backend.global.format.response.ErrorCode;

public class ReviewPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public ReviewPermissionDenied( ) {
        this.errorCode = ErrorCode.REVIEW_PERMISSION_DENIED;
    }
}
