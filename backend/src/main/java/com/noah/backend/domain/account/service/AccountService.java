package com.noah.backend.domain.account.service;

import com.noah.backend.domain.account.dto.requestDto.AccountPostDto;

public interface AccountService {

    Long createAccount(AccountPostDto accountPostDto);
}
