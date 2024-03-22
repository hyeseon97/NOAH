package com.noah.backend.domain.bank.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankHolderCheckReqDto { //예금주 조회 요청Dto
	String bankCode; //확인할 예금주의 은행코드(001. 한국은행 002. 산업은행 003. 기업은행 004. 국민은행)
	String accountNo; //확인할 예금주의 계좌번호
}
