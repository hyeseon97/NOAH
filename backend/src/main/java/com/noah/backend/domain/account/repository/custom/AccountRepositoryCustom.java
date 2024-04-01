package com.noah.backend.domain.account.repository.custom;

import com.noah.backend.domain.account.dto.responseDto.AccountIncludeIsAutoTransfer;
import com.noah.backend.domain.account.dto.responseDto.AccountInfoDto;
import com.noah.backend.domain.account.entity.Account;

import java.util.List;
import java.util.Optional;

public interface AccountRepositoryCustom {
    Optional<List<AccountInfoDto>> getMyAccountByMemberId(Long memberId);

    Optional<List<AccountIncludeIsAutoTransfer>> getAccountListIncludeIsAutoTransfer(Long memberId, Long travelId);
}
