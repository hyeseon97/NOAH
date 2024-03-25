package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailPlanNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailPlanNotFound(ErrorCode errorCode) {
        this.errorCode = ErrorCode.DETAILPLAN_NOT_FOUNT;
    }
}
