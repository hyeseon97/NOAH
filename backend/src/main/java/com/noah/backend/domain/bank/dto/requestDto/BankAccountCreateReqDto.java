package com.noah.backend.domain.bank.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountCreateReqDto {//계좌 생성 요청Dto
	String userKey; //SSAFY금융망에서 발급된 userKey
	String bankType; //선택한 은행코드:  001. 한국은행 002. 산업은행 003. 기업은행 004. 국민은행
	Long travelId;
}
