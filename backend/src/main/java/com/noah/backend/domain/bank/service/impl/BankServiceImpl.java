package com.noah.backend.domain.bank.service.impl;

import com.noah.backend.domain.bank.dto.requestDto.RequestHeaderDto;
import com.noah.backend.domain.bank.dto.responseDto.ProductDto;
import com.noah.backend.domain.bank.service.BankService;

import java.util.List;

public class BankServiceImpl implements BankService {


	@Override
	public List<ProductDto> productList() { //상품 조회 리스트
		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
		requestHeaderDto.setApiName("inquireBankAccountTypes");
		return null;
	}
}
