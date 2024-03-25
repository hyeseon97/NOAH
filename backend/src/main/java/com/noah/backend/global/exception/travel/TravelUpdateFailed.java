package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;

public class TravelUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public TravelUpdateFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.TRAVEL_UPDATE_FAILED;
    }
}
