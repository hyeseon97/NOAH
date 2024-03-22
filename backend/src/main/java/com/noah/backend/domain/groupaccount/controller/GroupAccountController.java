package com.noah.backend.domain.groupaccount.controller;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.groupaccount.service.GroupAccountService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "모임통장 컨트롤러", description = "Group Account Controller API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/groupaccount")
public class GroupAccountController {

    private final ApiResponse response;
    private final GroupAccountService groupAccountService;

    @Operation(summary = "모임 통장 생성", description = "모임 통장 생성")
    @PostMapping
    public ResponseEntity<?> createGroupAccount(@RequestBody GroupAccountPostDto groupAccountPostDto){
        Long groupAccountId = groupAccountService.createGroupAccount(groupAccountPostDto);
        return response.success(ResponseCode.GROUP_ACCOUNT_CREATED, groupAccountId);
    }

    @Operation(summary = "모임 단건 통장 조회", description = "모임 통장 단건 조회")
    @GetMapping("/{id}")
    public ResponseEntity<?> getGroupAccount(@PathVariable(name = "id") Long groupAccountId){
        GroupAccountInfoDto groupAccountInfoDto = groupAccountService.groupAccountInfo(groupAccountId);
        return response.success(ResponseCode.GROUP_ACCOUNT_INFO_FETCHED, groupAccountInfoDto);
    }

    @Operation(summary = "모임 통장 내용 수정", description = "목표금액, 납입금, 납부일, 수정")
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGroupAccount(@RequestBody GroupAccountUpdateDto groupAccountUpdateDto){

        return response.success(ResponseCode.GROUP_ACCOUNT_INFO_UPDATED, groupAccountService.updateGroupAccount(groupAccountUpdateDto));
    }
}
