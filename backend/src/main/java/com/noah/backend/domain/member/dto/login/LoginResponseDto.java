package com.noah.backend.domain.member.dto.login;

import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginResponseDto {

    private String token;
    private MemberInfoDto memberInfo;
}

