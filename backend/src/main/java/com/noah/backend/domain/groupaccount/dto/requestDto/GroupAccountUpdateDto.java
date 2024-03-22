package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class GroupAccountUpdateDto {
    private Long groupAccountId;
    private Long memberId;
    private int targetAmount;
    private int targetDate;
    private int perAmount;
    private int paymentDate;
}
