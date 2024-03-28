package com.noah.backend.global.exception.suggest;

import com.noah.backend.global.format.response.ErrorCode;
import lombok.Getter;

@Getter
public class LowerThanPriceNotExists extends RuntimeException{
	private final ErrorCode errorCode;

	public LowerThanPriceNotExists() {
		this.errorCode = ErrorCode.LOWER_THAN_PRICE_NOT_EXISTS;
	}
}
