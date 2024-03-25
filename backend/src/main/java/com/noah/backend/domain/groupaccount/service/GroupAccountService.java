package com.noah.backend.domain.groupaccount.service;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.memberTravel.dto.Response.MemberTravelListGetDto;

import java.util.List;

public interface GroupAccountService {

    List<GroupAccountInfoDto> getGroupAccountListByMemberId(Long memberId);

    Long createGroupAccount(GroupAccountPostDto groupAccountPostDto);

    GroupAccountInfoDto groupAccountInfo(Long groupAccountId);

    Long updateGroupAccount(Long memberId, GroupAccountUpdateDto groupAccountUpdateDto);

    int getTotalPay(Long travelId);

    List<MemberTravelListGetDto> getGroupAccountMembers(Long travelId);
}
