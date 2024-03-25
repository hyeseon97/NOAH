package com.noah.backend.global.exception.comment;

import com.noah.backend.global.format.response.ErrorCode;

public class CommentDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public CommentDeleteFailed( ) {
        this.errorCode = ErrorCode.COMMENT_DELETE_FAILED;
    }
}
