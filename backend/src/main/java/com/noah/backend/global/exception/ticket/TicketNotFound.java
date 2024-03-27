package com.noah.backend.global.exception.ticket;

import com.noah.backend.global.format.response.ErrorCode;

public class TicketNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public TicketNotFound( ) {
        this.errorCode = ErrorCode.TICKET_NOT_FOUND;
    }
}
