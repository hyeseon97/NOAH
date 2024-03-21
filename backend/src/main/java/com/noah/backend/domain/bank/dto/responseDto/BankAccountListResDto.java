package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountListResDto {//계좌 목록 조회 응답Dto
	String bankCode;
	String bankName;
	String username;
	String accountNo;
	int accountBalance;
}
