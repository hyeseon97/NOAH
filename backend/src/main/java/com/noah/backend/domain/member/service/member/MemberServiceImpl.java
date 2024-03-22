package com.noah.backend.domain.member.service.member;

import com.noah.backend.domain.member.dto.login.LoginRequestDto;
import com.noah.backend.domain.member.dto.login.LoginResponseDto;
import com.noah.backend.domain.member.dto.requestDto.SignupRequestDto;
import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import com.noah.backend.domain.member.entity.Member;
import com.noah.backend.domain.member.repository.MemberRepository;
import com.noah.backend.global.exception.member.EmailNotFoundException;
import com.noah.backend.global.exception.member.InvalidLoginAttemptException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.member.PasswordMismatchException;
import com.noah.backend.global.jwt.TokenInfo;
import com.noah.backend.global.jwt.provider.TokenProvider;
import com.noah.backend.global.jwt.repository.RefreshTokenRepository;
import com.noah.backend.global.jwt.service.TokenService;
import com.noah.backend.global.util.CookieUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final TokenProvider tokenProvider;
    private final TokenService tokenService;
    private final CookieUtil cookieUtil;
    private final PasswordEncoder passwordEncoder;
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    @Override
    public Long create(SignupRequestDto requestDto) {
        // 회원가입

        /* 싸피 금융망으로 UserKey 발급 */


        /* 회원 생성 */
        Member member = Member.builder()
                              .email(requestDto.getEmail())
                              .name(requestDto.getName())
                              .nickname(requestDto.getNickname())
                              .password(passwordEncoder.encode(requestDto.getPassword())).build();
        memberRepository.save(member);

        /* UserKey로 개인계좌 개설 */



        /* 은행 생성 */

        return member.getId();
    }

    @Override
    public boolean checkNicknameDuplication(String nickname) {
        // 닉네임 중복 체크

        return memberRepository.isNicknameDuplicate(nickname);
    }

    @Transactional
    @Override
    public LoginResponseDto login(LoginRequestDto requestDto, HttpServletResponse response) {
        // 로그인

        log.info("event=LoginAttempt, email={}", requestDto.getEmail());

        // 로그인한 이메일의 회원이 존재하는지 찾기
        Member member = memberRepository.findByEmail(requestDto.getEmail()).orElseThrow(EmailNotFoundException::new);
        // 패스워드 일치하는지 검사
        isPasswordMatchingWithEncoded(requestDto.getPassword(), member.getPassword());
        // old refresh 토큰 삭제
        removeOldRefreshToken(requestDto, member);

        // access 토큰, refresh 토큰 생성하기
        TokenInfo tokenInfo = tokenProvider.generateTokenInfo(member.getEmail());
        tokenService.saveToken(tokenInfo);
        // refresh 토큰 쿠키에 넣기
        cookieUtil.addCookie("RefreshToken", tokenInfo.getRefreshToken(), tokenProvider.getREFRESH_TOKEN_TIME(), response);

        // 로그인 응답
        return LoginResponseDto.builder()
                               .token(tokenInfo.getAccessToken())
                               .memberInfo(MemberInfoDto.builder()
                                                        .memberId(member.getId())
                                                        .email(member.getEmail())
                                                        .name(member.getName())
                                                        .nickname(member.getNickname())
                                                        .build())
                               .build();
    }

    private void isPasswordMatchingWithEncoded(String input, String encoded) {
        // 두개의 패스워드 일치 확인
        // input은 사용자가 입력한 패스워드, encoded는 DB에 있는 인코딩한 패스워드

        if (!passwordEncoder.matches(input, encoded)) {
            throw new InvalidLoginAttemptException();
        }
    }

    @Override
    @Transactional
    public String logout(String email, HttpServletResponse servletResponse) {
        // 로그아웃

        cookieUtil.removeCookie("RefreshToken", servletResponse);
        refreshTokenRepository.findById(email)
                              .ifPresent(refreshTokenRepository::delete);
        return email;
    }

    private void removeOldRefreshToken(LoginRequestDto requestDto, Member member) {
        // old refresh 토큰 삭제

        refreshTokenRepository.findById(member.getEmail())
                              .ifPresent(refreshTokenRepository::delete);
        log.info("event=DeleteExistingRefreshToken, email={}", requestDto.getEmail());
    }


    private void checkPasswordConfirmation(String password, String passwordConfirm) {
        // 회원가입 시 비밀번호 일치 확인
        // 둘 다 사용자가 입력한 패스워드

        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException();
        }
    }

    @Override
    public String test(String email) {
        Member member = memberRepository.findByEmail(email).orElseThrow(MemberNotFoundException::new);
        return member.getNickname();
    }

}
