package com.noah.backend.domain.member.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberInfoDto {

    /**
     *  회원 정보 조회
     *  MemberInfoDto와 이메일, 이름, 설졍 정보까지 들어있음
     */

    private Long memberId;
    private String email;
    private String name;
    private String nickname;

}
