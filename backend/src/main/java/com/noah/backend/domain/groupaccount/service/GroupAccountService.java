package com.noah.backend.domain.groupaccount.service;

import com.noah.backend.domain.groupaccount.dto.requestDto.GroupAccountPostDto;

public interface GroupAccountService {
    Long createGroupAccount(GroupAccountPostDto groupAccountPostDto);
}
