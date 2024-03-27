package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;

public class PlanDeleteFailed extends RuntimeException {

    private final ErrorCode errorCode;

    public PlanDeleteFailed( ) {
        this.errorCode = ErrorCode.PLAN_DELETE_FAILED;
    }
}
