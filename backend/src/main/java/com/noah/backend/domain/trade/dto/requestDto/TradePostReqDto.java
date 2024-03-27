package com.noah.backend.domain.trade.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradePostReqDto {
    private Long accountId;     // 계좌정보
    private String bankName;    // 은행이름
    private String name;        // 거래내용
    private String date;
    private String time;
    private int cost;
    private int amount;
    private int tradeType;
}
