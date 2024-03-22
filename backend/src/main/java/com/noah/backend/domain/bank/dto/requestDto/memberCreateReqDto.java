package com.noah.backend.domain.bank.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class memberCreateReqDto {//사용자 계정생성 요청Dto
	String email; //member 이메일
}
