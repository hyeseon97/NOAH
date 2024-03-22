package com.noah.backend.domain.bank.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankHolderCheckResDto { //예금주 조회 응답Dto
	String bankCode; //확인한 예금주의 은행 코드
	String bankName; //확인한 예금주의 은행 명
	String accountNo;//확인한 예금주의 계좌번호
	String userName;//확인한 예금주의 이름

}
