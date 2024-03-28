package com.noah.backend.global.exception.suggest;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class SuggestNotExists extends RuntimeException{
	private final ErrorCode errorCode;

	public SuggestNotExists() {
		this.errorCode = ErrorCode.SUGGEST_NOT_EXISTS;
	}
}
