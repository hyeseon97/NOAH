package com.noah.backend.global.exception.comment;

import com.noah.backend.global.format.response.ErrorCode;

public class CommentNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public CommentNotFound( ) {
        this.errorCode = ErrorCode.COMMENT_NOT_FOUND;
    }
}
