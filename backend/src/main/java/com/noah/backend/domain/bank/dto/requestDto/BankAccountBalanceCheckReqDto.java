package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountBalanceCheckReqDto {//계좌 잔액 조회 요청Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String bankCode; //잔액을 확인할 계좌의 은행코드
	String accountNo; //잔액을 확인할 계좌의 계좌 번호
}
