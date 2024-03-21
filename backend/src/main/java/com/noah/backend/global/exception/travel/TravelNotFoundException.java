package com.noah.backend.global.exception.travel;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class TravelNotFoundException extends RuntimeException{

    private final ErrorCode errorCode;

    public TravelNotFoundException(){
        this.errorCode = ErrorCode.TRAVEL_NOT_FOUND;

    }
}
