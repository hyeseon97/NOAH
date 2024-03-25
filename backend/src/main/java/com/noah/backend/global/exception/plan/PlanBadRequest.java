package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;

public class PlanBadRequest extends RuntimeException {

    private final ErrorCode errorCode;

    public PlanBadRequest(ErrorCode errorCode) {
        this.errorCode = ErrorCode.PLAN_BAD_REQUEST;
    }
}
