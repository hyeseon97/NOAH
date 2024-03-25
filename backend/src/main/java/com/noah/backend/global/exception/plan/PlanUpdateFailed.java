package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;

public class PlanUpdateFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public PlanUpdateFailed(ErrorCode errorCode) {
        this.errorCode = ErrorCode.PLAN_UPDATE_FAILED;
    }
}
