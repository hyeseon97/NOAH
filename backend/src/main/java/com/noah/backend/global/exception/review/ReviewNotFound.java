package com.noah.backend.global.exception.review;

import com.noah.backend.global.format.response.ErrorCode;

public class ReviewNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public ReviewNotFound(ErrorCode errorCode) {
        this.errorCode = ErrorCode.REVIEW_NOT_FOUND;
    }
}
