package com.noah.backend.domain.account.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRegistDto {
    private Long accountId;
    private Long travelId;
}
