package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class TravelMemberNotFoundException extends RuntimeException {
    private final ErrorCode errorCode;

    public TravelMemberNotFoundException() {
        this.errorCode = ErrorCode.TRAVEL_MEMBER_NOT_FOUND;
    }
}
