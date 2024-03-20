package com.noah.backend.domain.account.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AmountUpdateDto {
    private Long accountId;
    private Long ownerId;
    private int amount;
}
