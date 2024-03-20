package com.noah.backend.domain.groupaccount.service;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.requestDto.AmountUpdateDto;
import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;
import com.noah.backend.domain.groupaccount.entity.GroupAccount;

import java.util.List;

public interface GroupAccountService {

    Long createAccount(GroupAccountPostDto groupAccountPostDto);

    List<GroupAccount> getMyAccountList(Long memberId);

    GroupAccountInfoDto getAccountInfo(Long accountId);

    Long updateAmount(AmountUpdateDto amountUpdateDto);


    Long updateAccount(GroupAccountUpdateDto groupAccountUpdateDto);      // 납입금, 목표금액 등 수정

    void deleteAccount(Long accountId);





}
