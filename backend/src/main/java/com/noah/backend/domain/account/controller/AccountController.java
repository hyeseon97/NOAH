package com.noah.backend.domain.account.controller;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.requestDto.AccountUpdateDto;
import com.noah.backend.domain.account.dto.requestDto.AutoTransferPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.repository.AccountRepository;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.domain.memberTravel.Service.MemberTravelService;
import com.noah.backend.domain.memberTravel.entity.MemberTravel;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Account 컨트롤러", description = "Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/account")
public class AccountController {

    private final ApiResponse response;
    private final AccountService accountService;
    private final MemberService memberService;
    private final MemberTravelService memberTravelService;

    @Operation(summary = "계좌 생성", description = "계좌 생성")
    @PostMapping
    public ResponseEntity<?> createAccount(@Parameter(hidden = true) Authentication authentication,
                                           @RequestBody AccountPostDto accountPostDto) {
        Long memberId = memberService.searchMember(authentication).getMemberId();
        Long accountId = accountService.createAccount(accountPostDto);
        return response.success(ResponseCode.ACCOUNT_CREATED, accountId);
    }

    @Operation(summary = "멤버별 계좌 조회", description = "멤버별 계좌 조회")
    @GetMapping("/my")
    public ResponseEntity<?> getMyAccountList(@Parameter(hidden = true) Authentication authentication){
        Long memberId = memberService.searchMember(authentication).getMemberId();
        List<AccountInfoDto> accountInfoList = accountService.getMyAccountList(memberId);
        if(accountInfoList.isEmpty()){
            return response.success(ResponseCode.ACCOUNT_LIST_NOT_FOUND, null);
        };
        return response.success(ResponseCode.ACCOUNT_LIST_FETCHED, accountService.getMyAccountList(memberId));
    }

    @Operation(summary = "계좌 잔액 업데이트", description = "계좌 잔액 업데이트")
    @PutMapping
    public ResponseEntity<?> updateAmount(@RequestBody AccountUpdateDto accountUpdateDto){
        Long accountId = accountService.updateAmount(accountUpdateDto);
        return response.success(ResponseCode.ACCOUNT_INFO_UPDATED, accountId);
    }

    @Operation(summary = "여행에 자동이체 계좌 설정", description = "개인계좌를 자동이체 계좌로 등록 및 설정")
    @PostMapping("/auto")
    public ResponseEntity<?> setAutoTransfer(@Parameter(hidden = true) Authentication authentication, @RequestBody AutoTransferPostDto autoTransferPostDto){
        memberTravelService.setAutoTransfer(authentication.getName(), autoTransferPostDto);

        return response.success(ResponseCode.ACCOUNT_AUTO_SET_SUCCESS);
    }

    @Operation(summary = "여행에 자동이체 계좌 해제", description = "자동이체 등록된 계좌 해제")
    @DeleteMapping("/auto/{travelId}")
    public ResponseEntity<?> deleteAutoTransfer(@Parameter(hidden = true) Authentication authentication, @PathVariable(name = "travelId") Long travelId){
        memberTravelService.deleteAutoTransfer(authentication.getName(), travelId);

        return response.success(ResponseCode.ACCOUNT_AUTO_SET_SUCCESS);
    }
}
