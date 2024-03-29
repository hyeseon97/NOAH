package com.noah.backend.domain.member.dto.requestDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupRequestDto {

    @Email(message = "올바른 형식의 이메일 주소를 입력해 주십시오.")
    @NotEmpty(message = "이메일 필드는 필수 정보입니다. 공란으로 두실 수 없습니다.")
    @Schema(description = "이메일", example = "noah@naver.com")
    private String email;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d!@\\$%^&]{5,}$",
             message = "비밀번호는 알파벳, 숫자 각가 1개이상 포함하고 5자 이상이어야 합니다. (특수문자 ! @ $ % ^ & 만 가능)")
    @Schema(description = "비밀번호", example = "noah12")
    private String password;

    @Pattern(regexp = "^([가-힇]){2,5}$",
             message = "이름은 한글(자음 또는 모음만 존재하는 것 제외)을 조합하여 2~5자 이내여야 합니다.")
    @Schema(description = "이름", example = "김노아")
    private String name;


    @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣ]{2,8}$",
             message = "닉네임은 한글만 가능하고 2~8자 이내여야 합니다.")
    @Schema(description = "닉네임", example = "노아")
    private String nickname;

}
