package com.noah.backend.domain.trade.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TradeGetReqDto {
    private Long travelId;
    private String startDate;
    private String endDate;
    private String transactionType; // 구분 (M : 입금, D : 출금, A : 전체
    private String orderByType;     // 정렬순서 (ASC : 오름차순, DESC : 내림차순)
}
