package com.noah.backend.global.exception.groupaccount;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class GroupAccountAccessDeniedException extends RuntimeException {
    private final ErrorCode errorCode;

    public GroupAccountAccessDeniedException() {
        this.errorCode = ErrorCode.GROUP_ACCOUNT_ACCESS_DENIED;
    }
}
