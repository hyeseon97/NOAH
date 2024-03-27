package com.noah.backend.domain.bank.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountDepositReqDto {//계좌 입금 요청Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String bankCode; //입금할 계좌의 은행코드
	String accountNo; //입금할 계좌의 계좌번호
	int transactionBalance; //입금 금액
	String transactionSummary; //입금 내용 요약(입금자 명 입력할것.)
}
