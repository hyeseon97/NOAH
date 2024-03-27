package com.noah.backend.global.exception.flight;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class AirportNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public AirportNotFoundException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}