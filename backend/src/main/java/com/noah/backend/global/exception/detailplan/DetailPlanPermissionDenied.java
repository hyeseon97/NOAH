package com.noah.backend.global.exception.detailplan;

import com.noah.backend.global.format.response.ErrorCode;

public class DetailPlanPermissionDenied extends RuntimeException {

    private final ErrorCode errorCode;

    public DetailPlanPermissionDenied( ) {
        this.errorCode = ErrorCode.DETAILPLAN_PERMISSION_DENIED;
    }
}
