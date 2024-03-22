package com.noah.backend.global.handler;

import com.noah.backend.global.exception.account.AccountNotFoundException;
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

    @ExceptionHandler(TravelNotFoundException.class)
    protected ResponseEntity<?> handle(TravelNotFoundException e){
        log.error("TravelNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(AccountNotFoundException.class)
    protected ResponseEntity<?> handle(AccountNotFoundException e){
        log.error("AccountNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(GroupAccountNotFoundException.class)
    protected ResponseEntity<?> handle(GroupAccountNotFoundException e){
        log.error("GroupNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }

    @ExceptionHandler(MemberNotFoundException.class)
    protected ResponseEntity<?> handle(MemberNotFoundException e){
        log.error("MemberNotFoundException = {}", e.getErrorCode().getMessage());
        return response.error(e.getErrorCode());
    }
}
