package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;

public class TravelBadRequest extends RuntimeException {

    private final ErrorCode errorCode;

    public TravelBadRequest( ) {
        this.errorCode = ErrorCode.TRAVEL_BAD_REQUEST;
    }
}
