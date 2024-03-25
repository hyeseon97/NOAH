package com.noah.backend.global.exception.ticket;

import com.noah.backend.global.format.response.ErrorCode;

public class TicketUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public TicketUpdateFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.TICKET_UPDATE_FAILED;
    }
}
