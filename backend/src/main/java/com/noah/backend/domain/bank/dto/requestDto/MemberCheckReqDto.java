package com.noah.backend.domain.bank.dto.requestDto;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberCheckReqDto {
	String email;
}
