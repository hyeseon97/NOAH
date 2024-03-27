package com.noah.backend.global.exception.image;

import com.noah.backend.global.format.response.ErrorCode;

public class ImageUploadFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public ImageUploadFailed( ) {
        this.errorCode = ErrorCode.IMAGE_UPLOAD_FAILED;
    }
}
