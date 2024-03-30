package com.noah.backend.domain.member.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.member.dto.email.EmailRequestDto;
import com.noah.backend.domain.member.dto.email.EmailVerificationRequestDto;
import com.noah.backend.domain.member.dto.login.LoginRequestDto;
import com.noah.backend.domain.member.dto.requestDto.NicknameRequestDto;
import com.noah.backend.domain.member.dto.requestDto.SignupRequestDto;
import com.noah.backend.domain.member.dto.responseDto.MemberInfoDto;
import com.noah.backend.domain.member.service.mail.MailService;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.global.annotation.AccessToken;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import com.noah.backend.global.jwt.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Tag(name = "Member 컨트롤러", description = "Member Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
public class MemberController {

    private final ApiResponse response;
    private final TokenService tokenService;
    private final MailService mailService;
    private final MemberService memberService;

    @Operation(summary = "토큰 갱신", description = "리프레쉬 토큰을 통해 액세스 토큰 재발급 요청")
    @PutMapping
    public ResponseEntity<?> token(@AccessToken @RequestBody String accessToken) {
        return response.success(tokenService.reIssueAccessToken(accessToken));
    }

    @Operation(summary = "일반 회원가입", description = "일반 회원가입")
    @PostMapping
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequestDto requestDto,
                                    BindingResult bindingResult) throws IOException {

        if (bindingResult.hasErrors()) {
            return response.fail(bindingResult);
        }

        Long savedId = memberService.create(requestDto);
        return response.success(ResponseCode.MEMBER_SIGNUP_SUCCESS, savedId);
    }

    @Operation(summary = "이메일 인증코드 요청", description = "이메일 주소 보내면 인증코드를 메일로 보내는 요청")
    @PostMapping("/email")
    public ResponseEntity<?> emailVerification(@Valid @RequestBody EmailRequestDto requestDto,
                                               BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return response.fail(bindingResult);
        }

        mailService.sendEmailVerification(requestDto.getEmail());
        return response.success(ResponseCode.EMAIL_VERIFICATION_SENT.getMessage());
    }

    @Operation(summary = "인증코드 확인 요청", description = "인증코드가 메일로 보낸 인증코드와 일치하는 지 확인")
    @PostMapping("/email/verification")
    public ResponseEntity<?> emailVerify(@Valid @RequestBody EmailVerificationRequestDto requestDto,
                                         BindingResult bindingResult,
                                         HttpServletResponse servletResponse) {

        if (bindingResult.hasErrors()) {
            return response.fail(bindingResult);
        }

        return response.success(ResponseCode.EMAIL_VERIFIED_SUCCESS,
                                mailService.confirmAuthCode(requestDto.getEmail(), requestDto.getAuthNum(), servletResponse));
    }

    @Operation(summary = "닉네임 중복 검사", description = "닉네임의 중복 여부를 검사")
    @PostMapping("/nickname")
    public ResponseEntity<?> nicknameExists(@Valid @RequestBody NicknameRequestDto nickname, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return response.fail(bindingResult);
        }

        boolean result = memberService.checkNicknameDuplication(nickname.getNickname());
        return response.success(result ? ResponseCode.DUPLICATE_NICKNAME : ResponseCode.NICKNAME_AVAILABLE, result);
    }


    @Operation(summary = "일반 로그인", description = "일반 로그인")
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDto requestDto,
                                   BindingResult bindingResult,
                                   HttpServletResponse httpServletResponse) {

        if (bindingResult.hasErrors()) {
            return response.fail(bindingResult);
        }

        return response.success(ResponseCode.LOGIN_SUCCESS, memberService.login(requestDto, httpServletResponse));
    }

    @Operation(summary = "로그아웃", description = "로그아웃")
    @PostMapping("/logout")
    public ResponseEntity<?> logout(@Parameter(hidden = true) Authentication authentication,
                                    HttpServletResponse servletResponse) {
        return response.success(ResponseCode.LOGOUT_SUCCESS, memberService.logout(authentication.getName(), servletResponse));
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@Parameter(hidden = true) Authentication authentication){
        // authentication.getName() 하면 로그인한 사용자의 이메일이 나옴
        // 그리고 memberRepository.findByEmail(authentication.getName()) 하면 사용자 엔티티가 나옴
        // 근데 이걸 컨트롤러에서 사용하지 말고 이메일(authentication.getName())을 서비스로 가져가서
        // 서비스에서 memberRepository.findByEmail() 메서드 사용해서 엔티티 꺼내서 사용하기

        // 컨트롤러에서는 서비스 메서드만
        // 서비스에서는 레포지토리 메서드만
        // 사용하는게 좋다

        // 아래는 예시
        String email = authentication.getName();
        String nickname = memberService.test(email);
        return new ResponseEntity<String>(nickname, HttpStatus.OK);
    }

}

