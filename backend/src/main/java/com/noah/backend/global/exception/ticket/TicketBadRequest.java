package com.noah.backend.global.exception.ticket;

import com.noah.backend.global.format.response.ErrorCode;

public class TicketBadRequest extends RuntimeException {

    private final ErrorCode errorCode;

    public TicketBadRequest( ) {
        this.errorCode = ErrorCode.TICKET_BAD_REQUEST;
    }
}
