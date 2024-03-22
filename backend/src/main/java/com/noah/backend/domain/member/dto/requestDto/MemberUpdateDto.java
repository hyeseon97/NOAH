package com.noah.backend.domain.member.dto.requestDto;

import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberUpdateDto {

    /**
     *  회원정보 수정 요청
     */
    private Long memberId;

    @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣ]{2,8}$",
             message = "닉네임은 한글만 가능하고 2~8자 이내여야 합니다.")
    private String nickname;

}
