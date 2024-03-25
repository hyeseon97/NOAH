package com.noah.backend.global.exception.image;

import com.noah.backend.global.format.response.ErrorCode;

public class ImageProcessingFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public ImageProcessingFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.IMAGE_PROCESSING_FAILED;
    }
}
