package com.noah.backend.domain.groupaccount.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GroupAccountPostDto {
    private Long travelId;      // 여행
    private Long accountId;     // 계좌
    private int targetAmount;   // 목표금액
    private int targetDate;     // 목표기간
    private int perAmount;      // 월 납입금
    private int paymentDate;    // 월납입일
}
