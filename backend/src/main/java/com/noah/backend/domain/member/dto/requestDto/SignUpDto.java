package com.noah.backend.domain.member.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
	int memberId;
	String email = "dldnwlstest7@ssafy.co.kr";
	String password;
	String name;
	String nickname;
	String userKey;
}
