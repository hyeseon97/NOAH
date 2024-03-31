package com.noah.backend.global.handler;

import com.noah.backend.global.exception.account.AccountNotFoundException;
import com.noah.backend.global.exception.bank.*;
import com.noah.backend.global.exception.groupaccount.GroupAccountAccessDeniedException;
import com.noah.backend.global.exception.member.AccessTokenNotFoundException;
import com.noah.backend.global.exception.member.DuplicateEmailException;
import com.noah.backend.global.exception.member.EmailNotFoundException;
import com.noah.backend.global.exception.member.InvalidLoginAttemptException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.member.PasswordMismatchException;
import com.noah.backend.global.exception.member.RefreshTokenNotFoundException;
import com.noah.backend.global.exception.member.UnauthorizedAccessException;
import com.noah.backend.global.exception.groupaccount.GroupAccountNotFoundException;
import com.noah.backend.global.exception.member.MemberNotFoundException;
import com.noah.backend.global.exception.membertravel.MemberTravelAccessException;
import com.noah.backend.global.exception.membertravel.MemberTravelAlreadyExistException;
import com.noah.backend.global.exception.membertravel.MemberTravelAlreadyInvitedException;
import com.noah.backend.global.exception.notification.FirebaseTokenNotExistException;
import com.noah.backend.global.exception.notification.NotificationAccessException;
import com.noah.backend.global.exception.notification.NotificationNotFoundException;
import com.noah.backend.global.exception.notification.NotificationSendFailedException;
import com.noah.backend.global.exception.plan.PlanAccessException;
import com.noah.backend.global.exception.suggest.LowerThanPriceNotExists;
import com.noah.backend.global.exception.suggest.SuggestNotExists;
import com.noah.backend.global.exception.trade.TradeAccessException;
import com.noah.backend.global.exception.trade.TradeNotFoundException;
import com.noah.backend.global.exception.travel.TravelMemberNotFoundException;
import com.noah.backend.global.exception.travel.TravelNotFoundException;
import com.noah.backend.global.format.code.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ApiResponse response;

    @ExceptionHandler(RefreshTokenNotFoundException.class)
    protected ResponseEntity<?> handle(RefreshTokenNotFoundException e) {
        log.error("RefreshTokenNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(AccessTokenNotFoundException.class)
    protected ResponseEntity<?> handle(AccessTokenNotFoundException e) {
        log.error("AccessTokenNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    protected ResponseEntity<?> handle(UnauthorizedAccessException e) {
        log.error("UnauthorizedAccessException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    protected ResponseEntity<?> handle(EmailNotFoundException e) {
        log.error("EmailNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(InvalidLoginAttemptException.class)
    protected ResponseEntity<?> handle(InvalidLoginAttemptException e) {
        log.error("InvalidLoginAttemptException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(DuplicateEmailException.class)
    protected ResponseEntity<?> handle(DuplicateEmailException e) {
        log.error("DuplicateEmailException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(PasswordMismatchException.class)
    protected ResponseEntity<?> handle(PasswordMismatchException e) {
        log.error("PasswordMismatchException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 여행 */
    @ExceptionHandler(TravelNotFoundException.class)
    protected ResponseEntity<?> handle(TravelNotFoundException e){
        log.error("TravelNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(TravelMemberNotFoundException.class)
    protected ResponseEntity<?> handle(TravelMemberNotFoundException e){
        log.error("TravelMemberNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 계좌 */
    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<?> handle(AccountNotFoundException e){
        log.error("AccountNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 모임 통장 */
    @ExceptionHandler(GroupAccountNotFoundException.class)
    protected ResponseEntity<?> handle(GroupAccountNotFoundException e){
        log.error("GroupNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GroupAccountAccessDeniedException.class)
    protected ResponseEntity<?> handle(GroupAccountAccessDeniedException e){
        log.error("GroupAccessDeniedException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    /* 거래내역 */
    @ExceptionHandler(TradeNotFoundException.class)
    protected ResponseEntity<?> handle(TradeNotFoundException e){
        log.error("TradeNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(TradeAccessException.class)
    protected ResponseEntity<?> handle(TradeAccessException e){
        log.error("TradeAccessException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<?> handle(MemberNotFoundException e){
        log.error("MemberNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 알림 */
    @ExceptionHandler(NotificationNotFoundException.class)
    protected ResponseEntity<?> handle(NotificationNotFoundException e){
        log.error("NotificationNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(FirebaseTokenNotExistException.class)
    protected ResponseEntity<?> handle(FirebaseTokenNotExistException e){
        log.error("FirebaseTokenNotExistException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(NotificationSendFailedException.class)
    protected ResponseEntity<?> handle(NotificationSendFailedException e){
        log.error("NotificationSendFailedException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(NotificationAccessException.class)
    protected ResponseEntity<?> handle(NotificationAccessException e){
        log.error("NotificationAccessException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 은행 */
    @ExceptionHandler(A1001Exception.class)
    protected ResponseEntity<?> handle(A1001Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(A1003Exception.class)
    protected ResponseEntity<?> handle(A1003Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(A1011Exception.class)
    protected ResponseEntity<?> handle(A1011Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(A1014Exception.class)
    protected ResponseEntity<?> handle(A1014Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(A1016Exception.class)
    protected ResponseEntity<?> handle(A1016Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(A1017Exception.class)
    protected ResponseEntity<?> handle(A1017Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(A1018Exception.class)
    protected ResponseEntity<?> handle(A1018Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(H1008Exception.class)
    protected ResponseEntity<?> handle(H1008Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
    @ExceptionHandler(H1009Exception.class)
    protected ResponseEntity<?> handle(H1009Exception e){
        log.error("A1001Exception = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 제안 */
    @ExceptionHandler(SuggestNotExists.class)
    protected ResponseEntity<?> handle(SuggestNotExists e){
        log.error("SuggestNotExists = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(LowerThanPriceNotExists.class)
    protected ResponseEntity<?> handle(LowerThanPriceNotExists e){
        log.error("LowerThanPriceNotExists = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 멤버여행 */
    @ExceptionHandler(MemberTravelAccessException.class)
    protected ResponseEntity<?> handle(MemberTravelAccessException e){
        log.error("MemberTravelAccessException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(MemberTravelAlreadyInvitedException.class)
    protected ResponseEntity<?> handle(MemberTravelAlreadyInvitedException e){
        log.error("MemberTravelAlreadyInvitedException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(MemberTravelAlreadyExistException.class)
    protected ResponseEntity<?> handle(MemberTravelAlreadyExistException e){
        log.error("MemberTravelAlreadyExistException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    /* 계획 */
    @ExceptionHandler(PlanAccessException.class)
    protected ResponseEntity<?> handle(PlanAccessException e){
        log.error("PlanAccessException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

}
