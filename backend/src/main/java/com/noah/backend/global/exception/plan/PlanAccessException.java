package com.noah.backend.global.exception.plan;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class PlanAccessException extends RuntimeException{
    private final ErrorCode errorCode;

    public PlanAccessException() {
        this.errorCode = ErrorCode.PLAN_ACCESS_DENIED;
    }
}
