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
	String bankCode; //계좌의 은행 코드
	String bankName; //계좌의 은행 명
	String username; //회원의 이름
	String accountNo; //계좌의 계좌번호
	int accountBalance; //계좌 잔액
}
