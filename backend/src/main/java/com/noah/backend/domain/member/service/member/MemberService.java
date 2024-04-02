package com.noah.backend.domain.member.service.member;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.member.dto.login.LoginRequestDto;
import com.noah.backend.domain.member.dto.login.LoginResponseDto;
import com.noah.backend.domain.member.dto.requestDto.SignupRequestDto;
import com.noah.backend.domain.member.dto.responseDto.MemberSearchDto;
import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface MemberService {

    Long create(SignupRequestDto requestDto) throws IOException;

    boolean checkNicknameDuplication(String nickname);

    LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse httpServletResponse);

    String logout(String email, HttpServletResponse servletResponse);

    String test(String email);

    MemberSearchDto searchMember(Authentication authentication);

    Long findMemberId(String email);

}
