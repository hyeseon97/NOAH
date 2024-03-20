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
    ACCOUNT_CREATED(HttpStatus.OK, "계좌가 성공적으로 생성되었습니다."),
    ACCOUNT_LIST_FETCHED(HttpStatus.OK, "나의 계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_INFO_FETCHED(HttpStatus.OK, "계좌정보가 성공적으로 조회되었습니다."),
    ACCOUNT_INFO_UPDATED(HttpStatus.OK, "계좌정보가 성공적으로 수정되었습니다."),
    ACCOUNT_DELETED(HttpStatus.OK, "계좌가 성공적으로 삭제되었습니다.");

    private final HttpStatus status;
    private final String message;

}
