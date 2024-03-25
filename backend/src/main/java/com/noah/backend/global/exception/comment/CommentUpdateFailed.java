package com.noah.backend.global.exception.comment;

import com.noah.backend.global.format.response.ErrorCode;

public class CommentUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public CommentUpdateFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.COMMENT_UPDATE_FAILED;
    }
}
