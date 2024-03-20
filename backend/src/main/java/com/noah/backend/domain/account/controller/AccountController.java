package com.noah.backend.domain.account.controller;

import com.noah.backend.domain.account.dto.requestDto.*;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Account 컨트롤러", description = "Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

	//상품 조회
//	@GetMapping("/productlist")
//	@Operation(summary = "은행 별 계좌생성 가능목록", description = "계좌 상품 조회")
//	public ResponseEntity<?> productInquiry(){
//
//		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
//		requestHeaderDto.setApiName("inquireBankAccountTypes");
//		return new ResponseEntity<>(requestHeaderDto, HttpStatus.OK);
//	}

	//계좌 생성
//	@GetMapping("/create")
//	@Operation(summary = "계좌 생성", description = "유저 계좌 생성")
//	public ResponseEntity<?> createAccount(String email){
//
//		RequestHeaderDto requestHeaderDto = new RequestHeaderDto();
//		//email로 userKey를 얻었다고 가정
//		requestHeaderDto.setApiName("openAccount");
//		requestHeaderDto.setUserKey("유저userKey필요");
//		return new ResponseEntity<>(requestHeaderDto, HttpStatus.OK);
//	}

    private final ApiResponse response;
    private final AccountService accountService;
//    private MemberService memberService;

    @Operation(summary = "계좌 생성", description = "계좌 생성 확인용 실제로는 사용X")
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountPostDto accountPostDto){
Long result = accountService.createAccount(accountPostDto);
return response.success(ResponseCode.ACCOUNT_CREATED, result);
    }

//    @Operation(summary = "내가 속해있는 계좌 조회", description = "내가 속해있는 계좌 조회")
//    @GetMapping
//    public ResponseEntity<?> getMyAccountList(@Parameter(hidden = true) Authentication authentication) {
//
////        Member member = memberService.findMember(authentication.getName());
////        Long userId = member.getId();
//        return response.success(ResponseCode.ACCOUNT_LIST_FETCHED, accountService.getMyAccountList(userId));
//    }

    @Operation(summary = "계좌정보 조회", description = "단일 계좌정보 조회")
    @GetMapping("/{id}")
//    public ResponseEntity<?> getAccountInfo(@Parameter(hidden = true) Authentication authentication, @PathVariable(name = "id") Long accountId) {
    public ResponseEntity<?> getAccountInfo(@PathVariable(name = "id") Long accountId) {
        AccountInfoDto account = accountService.getAccountInfo(accountId);
        return response.success(ResponseCode.ACCOUNT_INFO_FETCHED, account);
    }

    @Operation(summary = "계좌 금액 최신화", description = "은행 api와 통신하여 계좌 정보 최신화 실제로 사용X")
    @PutMapping("/bank/{id}")
    public ResponseEntity<?> updateAmount(@Parameter(hidden = true) Authentication authentication, @RequestBody AmountUpdateDto amountUpdateDto) {
        Long accountId = accountService.updateAmount(amountUpdateDto);
        return response.success(ResponseCode.ACCOUNT_INFO_UPDATED, accountId);
    }
    @Operation(summary = "계좌 목표 수정", description = "납입금, 납입일, 목표금액 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateAccountInfo(@Parameter(hidden = true) Authentication authentication, @RequestBody AccountUpdateDto accountUpdateDto) {
        Long accountId = accountService.updateAccount(accountUpdateDto);
        return response.success(ResponseCode.ACCOUNT_INFO_UPDATED, accountId);
    }

    @Operation(summary = "계좌 삭제", description = "계좌 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAccount(@Parameter(hidden = true) Authentication authentication, @PathVariable(name = "id") Long accountId) {
        accountService.deleteAccount(accountId);
        return response.success(ResponseCode.ACCOUNT_DELETED);
    }


}
