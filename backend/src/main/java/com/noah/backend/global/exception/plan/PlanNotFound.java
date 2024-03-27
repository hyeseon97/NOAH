package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;

public class PlanNotFound extends RuntimeException {

    private final ErrorCode errorCode;

    public PlanNotFound() {
        this.errorCode = ErrorCode.PLAN_NOT_FOUND;
    }
}
