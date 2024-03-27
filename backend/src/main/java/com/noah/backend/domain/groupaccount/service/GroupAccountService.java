package com.noah.backend.domain.groupaccount.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.groupaccount.dto.requestDto.DepositReqDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface GroupAccountService {

    List<GroupAccountInfoDto> getGroupAccountListByMemberId(Long memberId) throws JsonProcessingException;

    Long createGroupAccount(GroupAccountPostDto groupAccountPostDto);

    GroupAccountInfoDto groupAccountInfo(Long groupAccountId);

    Long updateGroupAccount(Long memberId, GroupAccountUpdateDto groupAccountUpdateDto);

    int getTotalPay(Long travelId);

    List<MemberTravelListGetDto> getGroupAccountMembers(Long travelId);

    void depositIntoGroupAccount(String email, DepositReqDto depositReqDto) throws JsonProcessingException;

    void autoTransferGroupAccount() throws JsonProcessingException;
}
