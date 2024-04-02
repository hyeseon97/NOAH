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
    ACCOUNT_AUTO_SET_SUCCESS(HttpStatus.OK, "자동이체 계좌를 성공적으로 설정했습니다."),

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
    GROUP_ACCOUNT_LIST_NOT_FOUND(HttpStatus.OK, "조회되는 모임통장이 없습니다."),
    GROUP_ACCOUNT_TOTAL_PAY_INFO(HttpStatus.OK,"멤버별 모임통장 필수 납입금이 조회되었습니다."),
    GROUP_ACCOUNT_MEMBER_LIST_FETCHED(HttpStatus.OK, "모임통장에 속한 멤버 내역이 성공적으로 조회되었습니다."),

    DEPOSIT_SUCCESS(HttpStatus.OK, "이체가 성공적으로 완료되었습니다."),

    /* 거래내역 (Trade) */
    TRADE_CREATED(HttpStatus.OK, "거래내역이 성공적으로 생성되었습니다."),
    TRADE_INFO_FETCHED(HttpStatus.OK, "거래내역이 성공적으로 조회되었습니다."),
    TRADE_LIST_NOT_FOUND(HttpStatus.OK, "조회되는 거래내역이 없습니다."),
    TRADE_UPDATED(HttpStatus.OK, "거래정보가 성공적으로 수정되었습니다."),

    /* 환전 */
    EXCHANGE_SUCCESS(HttpStatus.OK, "환전이 성공적으로 완료되었습니다."),
    EXCHANGE_INFO_FETCHED(HttpStatus.OK, "환전 내역이 성공적으로 조회되었습니다."),
    EXCHANGE_RATE_INFO_FETCHED(HttpStatus.OK, "환율이 성공적으로 조회되었습니다."),
    EXCHANGE_NOT_FOUND(HttpStatus.OK, "환전내역이 없습니다."),

    /* 은행 (Bank) */
    BANK_DEPOSIT_SUCCESS(HttpStatus.OK,"입금이 성공적으로 완료되었습니다."),
    BANK_WITHDRAW_SUCCESS(HttpStatus.OK,"출금이 성공적으로 완료되었습니다."),
    BANK_TRANSFER_SUCCESS(HttpStatus.OK,"이체가 성공적으로 완료되었습니다."),
    TRANSACTION_HISTORY_SUCCESS(HttpStatus.OK,"거래내역조회가 성공적으로 완료되었습니다."),

    /* 여행(Travel) */
    TRAVEL_CREATED(HttpStatus.OK, "여행이 성공적으로 생성되었습니다."),
    TRAVEL_INFO_FETCHED(HttpStatus.OK, "여행 정보가 성공적으로 조회되었습니다."),
    TRAVEL_INFO_UPDATED(HttpStatus.OK, "여행 정보가 성공적으로 수정되었습니다."),
    TRAVEL_DELETED(HttpStatus.OK, "여행이 성공적으로 삭제되었습니다."),
    TRAVEL_INVITE_SUCCESS(HttpStatus.OK, "멤버를 성공적으로 초대했습니다."),

    /* 계획(Plan) */
    PLAN_CREATED(HttpStatus.OK, "계획이 성공적으로 생성되었습니다."),
    PLAN_INFO_FETCHED(HttpStatus.OK, "계획 정보가 성공적으로 조회되었습니다."),
    PLAN_INFO_UPDATED(HttpStatus.OK, "계획 정보가 성공적으로 수정되었습니다."),
    PLAN_DELETED(HttpStatus.OK, "계획이 성공적으로 삭제되었습니다."),

    /* 세부계획(DetailPlan) */
    DETAILPLAN_CREATED(HttpStatus.OK, "세부계획이 성공적으로 생성되었습니다."),
    DETAILPLAN_INFO_FETCHED(HttpStatus.OK, "세부계획 정보가 성공적으로 조회되었습니다."),
    DETAILPLAN_INFO_UPDATED(HttpStatus.OK, "세부계획 정보가 성공적으로 수정되었습니다."),
    DETAILPLAN_DELETED(HttpStatus.OK, "세부계획이 성공적으로 삭제되었습니다."),

    /* 멤버여행(TravelMember) */
    MEMBERTRAVEL_ADDED(HttpStatus.OK, "멤버가 여행에 성공적으로 추가되었습니다."),
    MEMBERTRAVEL_REMOVED(HttpStatus.OK, "멤버가 여행에서 성공적으로 제거되었습니다."),
    MEMBERTRAVEL_LIST_FETCHED(HttpStatus.OK, "멤버여행 목록이 성공적으로 조회되었습니다."),

    /* 리뷰(Review) */
    REVIEW_CREATED(HttpStatus.OK, "리뷰가 성공적으로 생성되었습니다."),
    REVIEW_FETCHED(HttpStatus.OK, "리뷰 정보가 성공적으로 조회되었습니다."),
    REVIEW_UPDATED(HttpStatus.OK, "리뷰가 성공적으로 수정되었습니다."),
    REVIEW_DELETED(HttpStatus.OK, "리뷰가 성공적으로 삭제되었습니다."),

    /* 추천(Suggest) */
    SUGGEST_FETCHED(HttpStatus.OK, "추천 정보가 성공적으로 조회되었습니다."),

    /* 댓글(Comment) */
    COMMENT_CREATED(HttpStatus.OK, "댓글이 성공적으로 생성되었습니다."),
    COMMENT_FETCHED(HttpStatus.OK, "댓글 정보가 성공적으로 조회되었습니다."),
    COMMENT_UPDATED(HttpStatus.OK, "댓글이 성공적으로 수정되었습니다."),
    COMMENT_DELETED(HttpStatus.OK, "댓글이 성공적으로 삭제되었습니다."),

    /* 이미지(Image) */
    IMAGE_UPLOADED(HttpStatus.OK, "이미지가 성공적으로 업로드되었습니다."),
    IMAGE_FETCHED(HttpStatus.OK, "이미지 정보가 성공적으로 조회되었습니다."),
    IMAGE_DELETED(HttpStatus.OK, "이미지가 성공적으로 삭제되었습니다."),

    /* 알림(Notification) */
    NOTIFICATION_TOKEN_SAVED(HttpStatus.OK, "파이어베이스 토큰이 저장되었습니다."),
    NOTIFICATION_LIST_FETCHED(HttpStatus.OK, "알림 리스트를 성공적으로 조회했습니다."),
    INVITE_ACCEPT(HttpStatus.OK, "여행 초대를 수락했습니다."),
    INVITE_REFUSE(HttpStatus.OK, "여행 초대를 거절했습니다."),
    NOTIFICATION_SEND_SUCCESS(HttpStatus.OK, "알람이 성공적으로 보내졌습니다."),
    NOTIFICATION_DELETED_SUCCESS(HttpStatus.OK, "알람이 성공적으로 삭제되었습니다."),

    /* 댓글(Comment) */
    TICKET_CREATED(HttpStatus.OK, "티켓이 성공적으로 생성되었습니다."),
    TICKET_FETCHED(HttpStatus.OK, "티켓 정보가 성공적으로 조회되었습니다."),
    TICKET_UPDATED(HttpStatus.OK, "티켓이 성공적으로 수정되었습니다."),
    TICKET_DELETED(HttpStatus.OK, "티켓이 성공적으로 삭제되었습니다."),

    /* 더미데이터 생성(csv) */
    DUMMY_SUCCESS(HttpStatus.OK, "더미데이터가 성공적으로 생성되었습니다.")
    ;



    private final HttpStatus status;
    private final String message;

}
