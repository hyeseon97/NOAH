package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountBalanceCheckResDto {//계좌 잔액 조회 응답Dto
	int accountBalance; //확인한 계좌의 잔액
}
