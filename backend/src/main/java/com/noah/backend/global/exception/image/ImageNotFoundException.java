package com.noah.backend.global.exception.image;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ImageNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public ImageNotFoundException( ) {
        this.errorCode = ErrorCode.IMAGE_NOT_FOUND;
    }
}
