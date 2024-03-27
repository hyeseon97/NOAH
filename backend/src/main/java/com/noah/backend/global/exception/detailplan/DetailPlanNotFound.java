package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailPlanNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailPlanNotFound( ) {
        this.errorCode = ErrorCode.DETAILPLAN_NOT_FOUNT;
    }
}
