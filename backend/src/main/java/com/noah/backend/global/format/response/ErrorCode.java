package com.noah.backend.global.format.response;

import com.noah.backend.domain.ticket.entity.Ticket;
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

    /* 환전 */
    EXCHANGE_NOT_FOUND(HttpStatus.NOT_FOUND, "환전내용을 찾을 수 없습니다."),
    EXCHANGE_FAILED(HttpStatus.NOT_ACCEPTABLE, "기존 환전 통화로만 환전할 수 있습니다."),
    EXCHANGE_CURRENCY_NOT_ACCEPTABLE(HttpStatus.NOT_ACCEPTABLE, "지원하지 않는 통화입니다."),

    /* 여행멤버 */
    MEMBER_TRAVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "여행에 속한 멤버를 찾을 수 없습니다."),

    // 항공(flight)
    REQUIRED_FIELD_FAILED(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    EXTERNAL_TOKEN_EXPIRED(HttpStatus.UNAUTHORIZED, "외부 API 토큰이 만료되었습니다."),

    /* 여행(Travel) */
    TRAVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "여행을 찾을 수 없습니다."),
    TRAVEL_MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "여행에 속한 멤버를 찾을 수 없습니다."),
    TRAVEL_BAD_REQUEST(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    TRAVEL_PERMISSION_DENIED (HttpStatus.FORBIDDEN, "여행 수정 권한이 없습니다."),
    TRAVEL_UPDATE_FAILED (HttpStatus.BAD_REQUEST, "여행 정보 업데이트에 실패했습니다."),
    TRAVEL_DELETE_FAILED (HttpStatus.INTERNAL_SERVER_ERROR, "여행 삭제에 실패했습니다."),

    /*티켓(Ticket)*/
    TICKET_NOT_FOUND(HttpStatus.NOT_FOUND, "티켓을 찾을 수 없습니다."),
    TICKET_BAD_REQUEST(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    TICKET_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "티켓 수정 권한이 없습니다."),
    TICKET_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "티켓 수정에 실패했습니다.."),
    TICKET_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "티켓 삭제에 실패했습니다."),

    /*계획(Plan)*/
    PLAN_NOT_FOUND(HttpStatus.NOT_FOUND,"계획을 찾을 수 없습니다."),
    PLAN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    PLAN_PERMISSION_DENIED (HttpStatus.FORBIDDEN, "계획 수정 권한이 없습니다."),
    PLAN_UPDATE_FAILED (HttpStatus.BAD_REQUEST, "계획 업데이트에 실패했습니다."),
    PLAN_DELETE_FAILED (HttpStatus.INTERNAL_SERVER_ERROR, "계획 삭제에 실패했습니다."),

    /*세부계획(DetailPlan)*/
    DETAILPLAN_NOT_FOUNT(HttpStatus.NOT_FOUND,"세부계획을 찾을 수 없습니다."),
    DETAILPLAN_BAD_REQUEST(HttpStatus.BAD_REQUEST, "필수 입력을 모두 입력하지 않았습니다."),
    DETAILPLAN_PERMISSION_DENIED (HttpStatus.FORBIDDEN, "세부계획 수정 권한이 없습니다."),
    DETAILPLAN_UPDATE_FAILED (HttpStatus.BAD_REQUEST, "세부계획 업데이트에 실패했습니다."),
    DETAILPLAN_DELETE_FAILED (HttpStatus.INTERNAL_SERVER_ERROR, "세부계획 삭제에 실패했습니다."),

    /*리뷰(Review)*/
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "리뷰를 찾을 수 없습니다."),
    REVIEW_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "리뷰 수정/삭제 권한이 없습니다."),
    REVIEW_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "리뷰 업데이트에 실패했습니다."),
    REVIEW_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "리뷰 삭제에 실패했습니다."),

    /*댓글(Comment)*/
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "댓글을 찾을 수 없습니다."),
    COMMENT_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "댓글 수정/삭제 권한이 없습니다."),
    COMMENT_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "댓글 업데이트에 실패했습니다."),
    COMMENT_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "댓글 삭제에 실패했습니다."),

    /*이미지(Image)*/
    IMAGE_NOT_FOUND(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다."),
    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 업로드에 실패했습니다."),
    IMAGE_PROCESSING_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "이미지 처리에 실패했습니다."),

    /*멤버여행(TravelMember)*/
    MEMBERTRAVEL_NOT_FOUND(HttpStatus.NOT_FOUND, "멤버여행을 찾을 수 없습니다."),
    MEMBERTRAVEL_PERMISSION_DENIED(HttpStatus.FORBIDDEN, "멤버여행 정보 수정 권한이 없습니다."),
    MEMBERTRAVEL_UPDATE_FAILED(HttpStatus.BAD_REQUEST, "멤버여행 정보 업데이트에 실패했습니다."),
    MEMBERTRAVEL_DELETE_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "멤버여행 삭제에 실패했습니다."),

    /* 알림(Notification) */
    NOTIFICATION_NOT_FOUND(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다."),

    /* 은행(Bank) */
    API_KEY_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, "API_KEY가 유효하지 않습니다."),
    USER_KEY_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, "USER_KEY가 유효하지 않습니다."),
    BANK_CODE_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, "은행코드가 유효하지 않습니다."),
    BANK_ACCOUNT_NUMBER_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, " 계좌번호가 유효하지 않습니다."),
    TRANSACTION_BALANCE_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, " 거래금액이 유효하지 않습니다."),
    TRANSACTION_BALANCE_LACK(HttpStatus.BAD_REQUEST, " 계좌 잔액이 부족하여 거래가 실패했습니다. "),
    EXCEEDED_TRANSFER_LIMIT_ONCE(HttpStatus.BAD_REQUEST, " 이체 가능 한도 초과(1회)."),
    EXCEEDED_TRANSFER_LIMIT_ONEDAY(HttpStatus.BAD_REQUEST, " 이체 가능 한도 초과(1일)."),
    TRANSACTION_SUMMARY_NOT_AVAILIABLE(HttpStatus.BAD_REQUEST, " 거래요약내용 길이가 초과되었습니다.");
    private final HttpStatus status;
    private final String message;

}
