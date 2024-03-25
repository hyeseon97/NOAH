package com.noah.backend.global.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 토큰 */
    ACCESS_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "액세스 토큰을 찾을 수 없습니다."),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 존재하지 않습니다."),
    REFRESH_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "새로운 로그인이 필요합니다. 재로그인을 진행해주세요."),

    /* 회원(Member) */
    EMAIL_DUPLICATED(HttpStatus.CONFLICT, "이미 사용 중인 이메일입니다."),
    EMAIL_NOT_FOUND(HttpStatus.BAD_REQUEST, "존재하지 않는 이메일입니다."),
    EMAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "인증 이메일 전송에 실패했습니다."),
    AUTH_CODE_INVALID(HttpStatus.BAD_REQUEST, "인증 코드가 유효하지 않습니다."),
    LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "이메일 혹은 패스워드 정보가 정확하지 않습니다."),
    PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다."),

    SIGNUP_FAILED(HttpStatus.BAD_REQUEST, "회원 가입에 실패했습니다."),
    MEMBER_NOT_FOUNT(HttpStatus.BAD_REQUEST, "회원이 존재하지 않습니다."),
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버를 찾을 수 없습니다."),

    /* 은행(bank) */
    BANK_ACCOUNT_CREATE_FAILED(HttpStatus.NOT_ACCEPTABLE, "은행 계좌 개설에 실패했습니다."),

    /* 계좌(Account) */
    ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "계좌를 찾을 수 없습니다."),

    /* 모임통장(GroupAccount) */
    GROUP_ACCOUNT_NOT_FOUND(HttpStatus.NOT_FOUND, "모임 통장을 찾을 수 없습니다."),
    GROUP_ACCOUNT_ACCESS_DENIED(HttpStatus.FORBIDDEN, "계좌 주인만 모임통장을 수정할 수 있습니다."),

    /* 거래내역(Trade) */
    TRADE_NOT_FOUND(HttpStatus.NOT_FOUND, "거래내역을 찾을 수 없습니다."),

    // 항공(flight)
    REQUIRED_FIELD_FAILED(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    EXTERNAL_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "외부 API 토큰이 만료되었습니다."),
    
    /* 여행(Travel) */
    TRAVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "여행을 찾을 수 없습니다."),
    TRAVEL_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "여행에 속한 멤버를 찾을 수 없습니다."),

    /* 알림(Notification) */
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다.")

    ;

    private final HttpStatus status;
    private final String message;

}
