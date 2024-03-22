package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountCreateResDto { //계좌 생성 응답Dto
	String bankName; //생성된 계좌의 은행(Account테이블 bank_name에 저장할것)
	String accountNumber; //생성된 계좌 번호(Account테이블 account_number에 저장할것)
}
