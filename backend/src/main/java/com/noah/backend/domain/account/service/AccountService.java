package com.noah.backend.domain.account.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.dto.requestDto.AccountUpdateDto;

import java.util.List;

public interface AccountService {

    Long createAccount(AccountPostDto accountPostDto);

    List<AccountInfoDto> getMyAccountList(Long memberId) throws JsonProcessingException;

    Long updateAmount(AccountUpdateDto accountUpdateDto);
}
