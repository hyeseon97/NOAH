package com.noah.backend.domain.bank.service;

import com.noah.backend.domain.bank.dto.responseDto.ProductDto;

import java.util.List;

//상품 조회
public interface BankService {
	List<ProductDto> productList(); //상품 조회 리스트
}
