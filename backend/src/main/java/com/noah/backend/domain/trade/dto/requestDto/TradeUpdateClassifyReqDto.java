package com.noah.backend.domain.trade.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TradeUpdateClassifyReqDto {
    private Long tradeId;
    private Long memberId;
    private String consumeType;
}
