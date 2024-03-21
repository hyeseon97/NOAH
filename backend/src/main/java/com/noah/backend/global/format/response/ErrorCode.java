package com.noah.backend.global.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 회원(Member) */
    SIGNUP_FAILED(HttpStatus.BAD_REQUEST, "회원 가입에 실패했습니다."),

    /* 계좌(Account) */
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "계좌를 찾을 수 없습니다."),

    // 항공(flight)
    REQUIRED_FIELD_FAILED(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    EXTERNAL_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "외부 API 토큰이 만료되었습니다.");

    private final HttpStatus status;
    private final String message;

}
