package com.noah.backend.global.exception.review;

import com.noah.backend.global.format.response.ErrorCode;

public class ReviewUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public ReviewUpdateFailed( ) {
        this.errorCode = ErrorCode.REVIEW_UPDATE_FAILED;
    }
}
