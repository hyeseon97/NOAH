package com.noah.backend.domain.bank.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountListReqDto {//계좌 목록 조회 요청Dto
	String userKey;//SSAFY금융망에서 발급된 userKey
}
