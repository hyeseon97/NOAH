package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountWithdrawReqDto {//계좌 출금 요청Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String bankCode; //출금할 계좌의 은행코드
	String accountNo; //출금할 계좌의 계좌번호
	int transactionBalance; //출금 금액
	String transactionSummary; //출금 내용 요약(출금자 명 입력할것.)
}
