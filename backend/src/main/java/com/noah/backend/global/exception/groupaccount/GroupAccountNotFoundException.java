package com.noah.backend.global.exception.groupaccount;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class GroupAccountNotFoundException extends RuntimeException{
    private final ErrorCode errorCode;
    public GroupAccountNotFoundException(){
        this.errorCode = ErrorCode.GROUP_ACCOUNT_NOT_FOUND;
    }
}
