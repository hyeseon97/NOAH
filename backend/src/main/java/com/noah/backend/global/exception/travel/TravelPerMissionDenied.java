package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;

public class TravelPerMissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public TravelPerMissionDenied( ) {
        this.errorCode = ErrorCode.TICKET_PERMISSION_DENIED;
    }
}
