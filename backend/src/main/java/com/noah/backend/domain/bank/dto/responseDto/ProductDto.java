package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
	String accountTypeUniqueNo; //상품 고유번호
	String bankCode; //은행코드
	String bankName; //은행명
	String accountTypeCode; //상품구분코드
	String accountTypeName; //상품구분명
	String accountName; //상품명
}
