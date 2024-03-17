package com.noah.backend.domain.account.service;

import com.noah.backend.domain.account.entity.Account;

import java.util.List;

public interface AccountService {

    List<Account> getMyAccountList(Long memberId);
}
