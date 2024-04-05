package com.noah.backend.global.exception.suggest;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class ReviewIdNotExists extends RuntimeException{
	private final ErrorCode errorCode;

	public ReviewIdNotExists() {
		this.errorCode = ErrorCode.REVIEW_ID_NOT_EXISTS;
	}
}
