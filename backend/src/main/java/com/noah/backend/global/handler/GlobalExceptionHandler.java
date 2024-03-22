package com.noah.backend.global.handler;

import com.noah.backend.global.exception.account.AccountNotFoundException;
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
