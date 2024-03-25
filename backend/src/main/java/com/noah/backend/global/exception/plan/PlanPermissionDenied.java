package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;

public class PlanPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public PlanPermissionDenied(ErrorCode errorCode) {
        this.errorCode = ErrorCode.PLAN_PERMISSION_DENIED;
    }
}
