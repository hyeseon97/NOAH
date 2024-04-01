package com.noah.backend.domain.trade.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradePostReqDto {
    private Long accountId;     // 계좌정보
    private String name;        // 거래내용
    private int cost;
    private int tradeType;
}
