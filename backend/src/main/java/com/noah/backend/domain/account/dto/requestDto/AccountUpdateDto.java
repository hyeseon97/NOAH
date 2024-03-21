package com.noah.backend.domain.account.dto.requestDto;

import lombok.Getter;

@Getter
public class AccountUpdateDto {
    private Long accountId;
    private int amount;
}
