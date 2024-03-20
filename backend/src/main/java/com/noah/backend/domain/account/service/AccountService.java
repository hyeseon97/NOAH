package com.noah.backend.domain.account.service;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.requestDto.AccountRegistDto;
import com.noah.backend.domain.account.dto.requestDto.AccountUpdateDto;
import com.noah.backend.domain.account.dto.requestDto.AmountUpdateDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;

import java.util.List;

public interface AccountService {

    Long createAccount(AccountPostDto accountPostDto);

    List<Account> getMyAccountList(Long memberId);

    AccountInfoDto getAccountInfo(Long accountId);

    Long updateAmount(AmountUpdateDto amountUpdateDto);

    Long registAccount(AccountRegistDto accountRegistDto);

    Long updateAccount(AccountUpdateDto accountUpdateDto);      // 납입금, 목표금액 등 수정

    void deleteAccount(Long accountId);





}
