package com.noah.backend.domain.bank.service;

import com.noah.backend.domain.bank.dto.responseDto.Product;

import java.util.List;

//상품 조회
public interface BankService {
	List<Product> productList(); //상품 조회 리스트
}
