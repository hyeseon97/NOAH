package com.noah.backend.domain.account.controller;

import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.global.format.code.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Account 컨트롤러", description = "Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private ApiResponse response;
    private AccountService accountService;
//    private MemberService memberService;

    @Operation(summary = "내가 속해있는 모임통장 조회", description = "내가 속해있는 모임통장 조회")
    @GetMapping
    public ResponseEntity<?> getMyAccountList(@Parameter(hidden = true) Authentication authentication) {
        return response.success();
    }

}
