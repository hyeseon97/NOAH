package com.noah.backend.domain.member.service.member;

import com.noah.backend.domain.member.dto.login.LoginRequestDto;
import com.noah.backend.domain.member.dto.login.LoginResponseDto;
import com.noah.backend.domain.member.dto.requestDto.SignupRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;

public interface MemberService {

    Long create(SignupRequestDto requestDto);

    boolean checkNicknameDuplication(String nickname);

    LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse httpServletResponse);

    String logout(String email, HttpServletResponse servletResponse);

    String test(String email);

    Long searchMemberId(Authentication authentication);

}
