package com.noah.backend.domain.groupaccount.repository.custom;

import com.noah.backend.domain.groupaccount.dto.responseDto.GroupAccountInfoDto;

import java.util.Optional;

public interface GroupAccountRepositoryCustom {

    Optional<GroupAccountInfoDto> getGroupAccountInfo(Long groupAccountId);
}
