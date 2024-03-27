package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepositReqDto {
    private Long accountId;
    private Long travelId;
    private int amount;
}
