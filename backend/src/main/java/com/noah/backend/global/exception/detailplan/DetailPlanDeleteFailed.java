package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailPlanDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailPlanDeleteFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.DETAILPLAN_DELETE_FAILED;
    }
}
