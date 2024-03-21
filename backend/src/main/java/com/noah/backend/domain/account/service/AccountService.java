package com.noah.backend.domain.account.service;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;

import java.util.List;

public interface AccountService {

    Long createAccount(AccountPostDto accountPostDto);

    List<AccountInfoDto> getMyAccountList(Long memberId);
}
