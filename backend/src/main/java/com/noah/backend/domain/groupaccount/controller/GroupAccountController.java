package com.noah.backend.domain.groupaccount.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.service.AccountService;
import com.noah.backend.domain.bank.dto.requestDto.BankAccountCreateReqDto;
import com.noah.backend.domain.bank.dto.responseDto.BankAccountCreateResDto;
import com.noah.backend.domain.bank.service.BankService;
import com.noah.backend.domain.groupaccount.dto.requestDto.DepositReqDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountRequestDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.groupaccount.service.GroupAccountService;
import com.noah.backend.domain.member.service.member.MemberService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "모임통장 컨트롤러", description = "Group Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groupaccount")
public class GroupAccountController {

    private final ApiResponse response;
    private final GroupAccountService groupAccountService;
    private final AccountService accountService;
    private final BankService bankService;
    private final MemberService memberService;

    @Operation(summary = "모임 통장 생성", description = "은행 계좌 생성 -> 우리 계좌 생성 -> 모임 통장 생성")
    @PostMapping
    public ResponseEntity<?> createGroupAccount(@Parameter(hidden = true) Authentication authentication,
                                                @RequestBody GroupAccountRequestDto groupAccountRequestDto) throws IOException {

        BankAccountCreateReqDto bankAccountCreateReqDto = BankAccountCreateReqDto.builder()
                .userKey(memberService.searchMember(authentication).getUserKey())
                .bankType(groupAccountRequestDto.getType())
                .travelId(groupAccountRequestDto.getTravelId())
                .build();

        /* 은행에서 계좌 생성 */
        BankAccountCreateResDto bankAccountCreateResDto = bankService.bankAccountCreate(bankAccountCreateReqDto);

        /* NOAH 서비스용 계좌 생성 */
        Long memberId = memberService.searchMember(authentication).getMemberId();
        Long travelId = bankAccountCreateReqDto.getTravelId();

        AccountPostDto accountPostDto = AccountPostDto.builder()
                .accountNumber(bankAccountCreateResDto.getAccountNumber())
                .bankName(bankAccountCreateResDto.getBankName())
                .memberId(memberId).travelId(travelId).build();

        Long accountId = accountService.createAccount(accountPostDto);

        /* 모임 통장 생성(설정) -> 계좌, 모임통장이 따로있어서 계좌를 만들고 모임통장으로 엮는 느낌임 */
        GroupAccountPostDto groupAccountPostDto = GroupAccountPostDto.builder()
                .accountId(accountId)
                .travelId(travelId).build();
        Long groupAccountId = groupAccountService.createGroupAccount(groupAccountPostDto);


        return response.success(ResponseCode.GROUP_ACCOUNT_CREATED, groupAccountId);
    }

    @Operation(summary = "모임 단건 통장 조회", description = "모임 통장 단건 조회")
    @GetMapping("/{groupAccountId}")
    public ResponseEntity<?> getGroupAccount(@PathVariable(name = "groupAccountId") Long groupAccountId) {
        GroupAccountInfoDto groupAccountInfoDto = groupAccountService.groupAccountInfo(groupAccountId);
        return response.success(ResponseCode.GROUP_ACCOUNT_INFO_FETCHED, groupAccountInfoDto);
    }

    @Operation(summary = "사용자가 속해있는 모임통장 전체조회", description = "사용자가 속해있는 모임통장 전체 조회")
    @GetMapping
    public ResponseEntity<?> getMyGroupAccountList(@Parameter(hidden = true) Authentication authentication) throws IOException {
        Long memberId = memberService.searchMember(authentication).getMemberId();
        List<GroupAccountInfoDto> result = groupAccountService.getGroupAccountListByMemberId(memberId);
        if (result.isEmpty()) {
            return response.success(ResponseCode.GROUP_ACCOUNT_LIST_NOT_FOUND, null);
        }
        return response.success(ResponseCode.GROUP_ACCOUNT_INFO_FETCHED, result);
    }

    @Operation(summary = "모임 통장 내용 수정", description = "목표금액, 납입금, 납부일, 수정")
    @PutMapping()
    public ResponseEntity<?> updateGroupAccount(@Parameter(hidden = true) Authentication authentication,
                                                @RequestBody GroupAccountUpdateDto groupAccountUpdateDto) {
        Long memberId = memberService.searchMember(authentication).getMemberId();
        return response.success(ResponseCode.GROUP_ACCOUNT_INFO_UPDATED, groupAccountService.updateGroupAccount(memberId, groupAccountUpdateDto));
    }

    @Operation(summary = "모임통장에 속해있는 멤버, 납입금 조회", description = "모임통장에 속해있는 멤버, 납입금 조회")
    @GetMapping("/member/{travelId}")
    public ResponseEntity<?> getGroupAccountMembers(@PathVariable(name = "travelId") Long travelId) {
        return response.success(ResponseCode.GROUP_ACCOUNT_MEMBER_LIST_FETCHED, groupAccountService.getGroupAccountMembers(travelId));
    }

    @Operation(summary = "모임통장 멤버별 필수 납입금 조회", description = "모임통장 멤버별 필수 납입금 조회")
    @GetMapping("/totalDue/{travelId}")
    public ResponseEntity<?> getTotalPayInfo(@PathVariable(name = "travelId") Long travelId) {
        int result = groupAccountService.getTotalPay(travelId);
        return response.success(ResponseCode.GROUP_ACCOUNT_TOTAL_PAY_INFO, result);
    }

    @Operation(summary = "모임통장에 입금", description = "모임통장에 입금")
    @PostMapping("/deposit")
    public ResponseEntity<?> depositIntoGroupAccount(@Parameter(hidden = true) Authentication authentication, @RequestBody DepositReqDto depositReqDto) throws IOException {
        groupAccountService.depositIntoGroupAccount(authentication.getName(), depositReqDto);
        return response.success(ResponseCode.DEPOSIT_SUCCESS);
    }
}
