package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailPlanBadRequest extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailPlanBadRequest(ErrorCode errorCode) {
        this.errorCode = ErrorCode.DETAILPLAN_BAD_REQUEST;
    }
}
