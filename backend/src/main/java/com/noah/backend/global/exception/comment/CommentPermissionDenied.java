package com.noah.backend.global.exception.comment;

import com.noah.backend.global.format.response.ErrorCode;

public class CommentPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public CommentPermissionDenied(ErrorCode errorCode) {
        this.errorCode = ErrorCode.COMMENT_PERMISSION_DENIED;
    }
}
