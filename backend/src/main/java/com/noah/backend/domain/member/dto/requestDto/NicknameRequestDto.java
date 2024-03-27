package com.noah.backend.domain.member.dto.requestDto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NicknameRequestDto {

    @Pattern(regexp = "^[가-힣ㄱ-ㅎㅏ-ㅣ]{2,8}$",
             message = "닉네임은 한글만 가능하고 2~8자 이내여야 합니다.")
    @Schema(description = "닉네임", example = "노아")
    private String nickname;

}
