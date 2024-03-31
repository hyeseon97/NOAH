package com.noah.backend.global.exception.csv;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class CsvCreateFailedException extends RuntimeException {

    private final ErrorCode errorCode;

    public CsvCreateFailedException( ) {
        this.errorCode = ErrorCode.DUMMYDATA_CREATE_FAILED;
    }
}
