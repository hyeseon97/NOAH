package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailUpdateFailed( ) {
        this.errorCode = ErrorCode.DETAILPLAN_UPDATE_FAILED;
    }
}
