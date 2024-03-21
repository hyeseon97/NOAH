package com.noah.backend.global.format.response;

import com.noah.backend.domain.account.entity.Account;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ResponseCode {

    /* 회원(Member) */
    MEMBER_SIGNUP_SUCCESS(HttpStatus.CREATED, "회원가입이 정상적으로 완료되었습니다."),
    NICKNAME_CHECK_SUCCESS(HttpStatus.OK, "닉네임 검사가 성공적으로 이루어졌습니다."),

    /* 계좌(Account) */
    ACCOUNT_LIST_FETCHED(HttpStatus.OK, "나의 계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_INFO_FETCHED(HttpStatus.OK, "계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_DELETED(HttpStatus.OK, "계좌가 성공적으로 삭제되었습니다."),

    // 항공(flight)
//    SERVER_ACCESS_SUCCESS(HttpStatus.OK, "서버 통신 성공"),
    FLIGHT_OFFERS_SUCCESS(HttpStatus.OK, "항공권을 성공적으로 조회했습니다."),
    FLIGHT_PRICE_ANALYSIS_SUCCESS(HttpStatus.OK, "항공권 가격 분석을 성공적으로 조회했습니다."),
    AIRPORT_RELEVANT_SUCCESS(HttpStatus.OK, "가까운 공항 정보를 성공적으로 조회했습니다."),
    AIRPORT_ROUTES_SUCCESS(HttpStatus.OK, "공항 직항 항로를 성공적으로 조회했습니다."),
    AIRLINE_CODES_SUCCESS(HttpStatus.OK, "항공사 코드를 성공적으로 조회했습니다."),
    AIRLINE_ROUTES_SUCCESS(HttpStatus.OK, "항공사가 제공하는 항로를 성공적으로 조회했습니다.");

    private final HttpStatus status;
    private final String message;

}
