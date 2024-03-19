package com.noah.backend.domain.account.service;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getMyAccountList(Long memberId);

    AccountInfoDto getAccountInfo(Long accountId);

    Long createAccount(Long memberId, AccountPostDto accountPostDto);

    void deleteAccount(Long accountId);

    Long updateAccount(Long accountId);



}
