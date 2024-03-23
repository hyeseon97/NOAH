package com.noah.backend.global.format.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    /* 회원(Member) */
    EMAIL_VERIFICATION_SENT(HttpStatus.OK, "이메일 인증코드가 성공적으로 발송되었습니다."),
    EMAIL_VERIFIED_SUCCESS(HttpStatus.OK, "이메일이 성공적으로 인증되었습니다."),
    MEMBER_SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 정상적으로 완료되었습니다."),
    NICKNAME_CHECK_SUCCESS(HttpStatus.OK, "닉네임 검사가 성공적으로 이루어졌습니다."),
    LOGIN_SUCCESS(HttpStatus.OK, "로그인이 성공적으로 이루어졌습니다."),
    LOGOUT_SUCCESS(HttpStatus.OK, "로그아웃이 성공적으로 이루어졌습니다."),
    NICKNAME_AVAILABLE(HttpStatus.OK, "사용 가능한 닉네임입니다"),
    DUPLICATE_NICKNAME(HttpStatus.OK, "중복된 닉네임입니다"),
    MEMBER_FETCHED(HttpStatus.OK, "회원이 성공적으로 조회되었습니다."),

    /* 계좌 (Account) */
    ACCOUNT_CREATED(HttpStatus.OK, "계좌가 성공적으로 생성되었습니다."),
    ACCOUNT_LIST_FETCHED(HttpStatus.OK, "나의 계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_LIST_NOT_FOUND(HttpStatus.OK, "조회되는 계좌 정보가 없습니다."),
    ACCOUNT_INFO_FETCHED(HttpStatus.OK, "계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_DELETED(HttpStatus.OK, "계좌가 성공적으로 삭제되었습니다."),
    ACCOUNT_INFO_UPDATED(HttpStatus.OK, "계좌정보가 성공적으로 수정되었습니다."),

    // 항공(flight)
//    SERVER_ACCESS_SUCCESS(HttpStatus.OK, "서버 통신 성공"),
    FLIGHT_OFFERS_SUCCESS(HttpStatus.OK, "항공권을 성공적으로 조회했습니다."),
    FLIGHT_PRICE_ANALYSIS_SUCCESS(HttpStatus.OK, "항공권 가격 분석을 성공적으로 조회했습니다."),
    AIRPORT_RELEVANT_SUCCESS(HttpStatus.OK, "가까운 공항 정보를 성공적으로 조회했습니다."),
    AIRPORT_ROUTES_SUCCESS(HttpStatus.OK, "공항 직항 항로를 성공적으로 조회했습니다."),
    AIRLINE_CODES_SUCCESS(HttpStatus.OK, "항공사 코드를 성공적으로 조회했습니다."),
    AIRLINE_ROUTES_SUCCESS(HttpStatus.OK, "항공사가 제공하는 항로를 성공적으로 조회했습니다."),

    /* 모임통장 (GroupAccount) */
    GROUP_ACCOUNT_CREATED(HttpStatus.OK, "모임통장이 성공적으로 생성되었습니다."),
    GROUP_ACCOUNT_INFO_FETCHED(HttpStatus.OK, "모임통장이 성공적으로 조회되었습니다."),
    GROUP_ACCOUNT_INFO_UPDATED(HttpStatus.OK, "모임통장 내용이 성공적으로 수정되었습니다."),

    /* 거래내역 (Trade) */
    TRADE_INFO_FETCHED(HttpStatus.OK, "거래내역이 성공적으로 조회되었습니다."),
    TRADE_LIST_NOT_FOUND(HttpStatus.OK, "조회되는 거래내역이 없습니다.")

    ;

    /* 여행(Travel) */


    private final HttpStatus status;
    private final String message;

}
