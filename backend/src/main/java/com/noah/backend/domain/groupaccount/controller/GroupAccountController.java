package com.noah.backend.domain.groupaccount.controller;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.service.GroupAccountService;
import com.noah.backend.global.format.code.ApiResponse;
import com.noah.backend.global.format.response.ResponseCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
