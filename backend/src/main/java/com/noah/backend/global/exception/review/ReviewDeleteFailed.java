package com.noah.backend.global.exception.review;

import com.noah.backend.global.format.response.ErrorCode;

public class ReviewDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public ReviewDeleteFailed( ) {
        this.errorCode = ErrorCode.REVIEW_DELETE_FAILED;
    }
}
