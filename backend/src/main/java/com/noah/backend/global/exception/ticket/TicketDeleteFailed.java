package com.noah.backend.global.exception.ticket;

import com.noah.backend.global.format.response.ErrorCode;

public class TicketDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public TicketDeleteFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.TICKET_DELETE_FAILED;
    }
}
