package com.noah.backend.domain.exchange.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ExchangeReqDto {
    Long travelId;
    String currency;        // 통화
    int amount;          // 환전금액
    int exchangeAmount;  // 외화금액
}
