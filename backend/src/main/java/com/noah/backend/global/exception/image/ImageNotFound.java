package com.noah.backend.global.exception.image;

import com.noah.backend.global.format.response.ErrorCode;

public class ImageNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public ImageNotFound( ) {
        this.errorCode = ErrorCode.IMAGE_NOT_FOUND;
    }
}
