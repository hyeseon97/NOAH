package com.noah.backend.domain.trade.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TradeGetResDto {
    Long tradeId;
    int type;
    String name;
    String date;
    String time;
    int cost;
    int amount;
    String consumeType;
    Long memberId;
    String memberName;
}
