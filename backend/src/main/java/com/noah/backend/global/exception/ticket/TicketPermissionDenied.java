package com.noah.backend.global.exception.ticket;

import com.noah.backend.global.format.response.ErrorCode;

public class TicketPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public TicketPermissionDenied(ErrorCode errorCode) {
        this.errorCode = ErrorCode.TICKET_PERMISSION_DENIED;
    }
}
