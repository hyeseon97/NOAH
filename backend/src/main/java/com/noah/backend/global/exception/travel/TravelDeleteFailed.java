package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;

public class TravelDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public TravelDeleteFailed( ) {
        this.errorCode = ErrorCode.TRAVEL_DELETE_FAILED;
    }
}
